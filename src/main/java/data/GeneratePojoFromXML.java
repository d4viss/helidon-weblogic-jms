package data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.helidon.messaging.connectors.jms.JmsMessage;
import model.Destination;
import model.JmsMessageHolder;

import java.util.HashMap;
import java.util.Map;

public class GeneratePojoFromXML {

    public static JmsMessageHolder convert(JmsMessage<String> entry) {
        String payload = entry.getPayload();
        String correlationId = entry.getCorrelationId();

        XmlMapper xmlMapper = new XmlMapper();
        Map<String, String> map = new HashMap<>();
        try {
            map = xmlMapper.readValue(payload, HashMap.class);
        } catch (JsonProcessingException e) {
            System.out.println("XmlProcessingException: " + e.getMessage());
        }

        return new JmsMessageHolder(map.get("message"), Destination.valueOf(map.get("destination")), correlationId);
    }
}
