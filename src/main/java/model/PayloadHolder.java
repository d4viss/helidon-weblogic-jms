package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@AllArgsConstructor
@JacksonXmlRootElement(localName = "root")
public class PayloadHolder {

    @JacksonXmlProperty(localName = "message")
    private String message;

    @JacksonXmlProperty(localName = "destination")
    private Constants destination;

    @JsonIgnore
    private String correlationId;

    @Override
    public String toString() {
        return "PayloadHolder [message=" + message + ", correlationId=" + correlationId + ", destination=" + destination + "]";
    }
}
