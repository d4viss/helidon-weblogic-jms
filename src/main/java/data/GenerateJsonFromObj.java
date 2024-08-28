package data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GenerateJsonFromObj {

    /**
     * convert an object to a Json
     * @param obj object to convert
     * @return Json from object
     */
    public static String convert(Object obj) {
        String json = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException: " + e.getMessage());
        }
        return json;
    }
}
