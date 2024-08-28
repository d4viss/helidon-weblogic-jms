package jms;

import io.helidon.messaging.connectors.jms.JmsMessage;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Listener {

    /**
     * print in console a message received by a queue
     * @param msg receive a message
     */
    @Incoming("from-wls-1")
    public void receiveTestQueue1(JmsMessage<String> msg) {
        System.out.println("received from from-wls-1: " + msg.getPayload() + "\nCorrelationId: " + msg.getCorrelationId());
    }

    /**
     * print in console a message received by a queue
     * @param msg receive a message
     */
    @Incoming("from-wls-2")
    public void receiveTestQueue2(JmsMessage<String> msg) {
        System.out.println("received from from-wls-2: " + msg.getPayload());
    }
}
