package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JacksonXmlRootElement(localName = "root")
public class JmsMessageHolder {

    @JacksonXmlProperty(localName = "message")
    private String message;

    @JacksonXmlProperty(localName = "destination")
    private Destination destination;

    @JsonIgnore
    private String correlationId;

    @Override
    public String toString() {
        return "PayloadHolder [message=" + message + ", correlationId=" + correlationId + ", destination=" + destination + "]";
    }
}
