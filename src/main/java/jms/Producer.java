package jms;

import data.GenerateJsonFromObj;
import model.PayloadHolder;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.reactivestreams.FlowAdapters;
import org.reactivestreams.Publisher;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.SubmissionPublisher;

@ApplicationScoped
public class Producer {

    private final SubmissionPublisher<String> emitterTestQueue = new SubmissionPublisher<>();
    private final SubmissionPublisher<String> emitterTestQueue2 = new SubmissionPublisher<>();

    /**
     * classify a payload
     * @param payload payload received
     */
    public void submit(PayloadHolder payload) {
        try {
            switch (payload.getDestination()){
                case DEST_TEST_QUEUE:
                    submitEmitter(emitterTestQueue, payload);
                    break;
                case DEST_TEST_QUEUE_2:
                    submitEmitter(emitterTestQueue2, payload);
                    break;
                default:
                    System.out.println("No matching destination");
                    break;
            }
        }catch (Exception e){
            System.out.println("Error in submitting message: " + e.getMessage());
        }
    }

    /**
     * send the payload with an emitter
     * @param emitter
     * @param payload
     */
    private void submitEmitter(SubmissionPublisher<String> emitter, PayloadHolder payload) {
        emitter.submit(GenerateJsonFromObj.convert(payload));
    }

    /**
     * to send a message to a queue
     * @return
     */
    @Outgoing("to-wls-1")
    public Publisher<String> produceTestQueue() {
        return FlowAdapters.toPublisher(emitterTestQueue);
    }

    /**
     * to send a message to a queue
     * @return
     */
    @Outgoing("to-wls-2")
    public Publisher<String> produceTestQueue2() {
        return FlowAdapters.toPublisher(emitterTestQueue2);
    }
}
