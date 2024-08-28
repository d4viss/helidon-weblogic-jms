package model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PayloadHolder {

    @JacksonXmlProperty(localName = "message")
    private String message;

    @JacksonXmlProperty(localName = "destination")
    private Constants destination;

    private String correlationId;

    @Override
    public String toString() {
        return "PayloadHolder [message=" + message + ", correlationId=" + correlationId + ", destination=" + destination + "]";
    }
}
