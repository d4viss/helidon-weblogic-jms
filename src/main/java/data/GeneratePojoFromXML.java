package data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.PayloadHolder;

public class GeneratePojoFromXML {

    public static PayloadHolder convert(String xml) {
        XmlMapper mapper = new XmlMapper();
        PayloadHolder payloadHolder = null;
        try {
            payloadHolder = mapper.readValue(xml, PayloadHolder.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return payloadHolder;
    }
}
