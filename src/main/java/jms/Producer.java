package jms;

import data.GenerateXMLFromPojo;
import io.helidon.messaging.connectors.jms.JmsMessage;
import model.JmsMessageHolder;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.reactive.streams.operators.PublisherBuilder;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
import org.reactivestreams.FlowAdapters;

import javax.enterprise.context.ApplicationScoped;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.concurrent.SubmissionPublisher;

@ApplicationScoped
public class Producer {

    private final SubmissionPublisher<String> emitterTestQueue = new SubmissionPublisher<>();
    private final SubmissionPublisher<String> emitterTestQueue2 = new SubmissionPublisher<>();
    private String correlationId = "";
    /**
     * classify a payload
     * @param holder payload received
     */
    public void submit(JmsMessageHolder holder) {
        try {
            switch (holder.getDestination()){
                case DEST_TEST_QUEUE:
                    this.correlationId = holder.getCorrelationId();
                    submitEmitter(emitterTestQueue, holder);
                    break;
                case DEST_TEST_QUEUE_2:
                    this.correlationId = holder.getCorrelationId();
                    submitEmitter(emitterTestQueue2, holder);
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
     * @param holder
     */
    private void submitEmitter(SubmissionPublisher<String> emitter, JmsMessageHolder holder) {
        emitter.submit(GenerateXMLFromPojo.convert(holder));
    }

    /**
     * create the publisher to send the JmsMessage
     * @param emitter
     * @return
     */
    private PublisherBuilder<Message<String>> produceMessage(SubmissionPublisher<String> emitter) {
        return ReactiveStreams.
                fromPublisher(FlowAdapters.toPublisher(emitter))
                .map(s -> JmsMessage.builder(s)
                        .customMapper((p, session) -> {
                            TextMessage textMessage = null;
                            try {
                                textMessage = session.createTextMessage(p);
                                textMessage.setJMSCorrelationID(this.correlationId);
                            }catch (JMSException e) {
                                System.out.println("Error in creating message: " + e.getMessage());
                            }
                            return textMessage;
                        })
                        .build());
    }

    /**
     * to send a message to a queue
     *
     * @return
     */
    @Outgoing("to-wls-1")
    public PublisherBuilder<Message<String>> produceTestQueue1() {
        return this.produceMessage(emitterTestQueue);
    }

    /**
     * to send a message to a queue
     * @return
     */
    @Outgoing("to-wls-2")
    public PublisherBuilder<Message<String>> produceTestQueue2() {
        return this.produceMessage(emitterTestQueue2);
    }
}
