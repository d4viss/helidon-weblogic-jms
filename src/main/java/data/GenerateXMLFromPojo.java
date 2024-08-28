package data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class GenerateXMLFromPojo {

    public static String convert(Object obj){
        XmlMapper mapper = new XmlMapper();
        String xml = null;
        try {
            xml = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return xml;
    }
}
