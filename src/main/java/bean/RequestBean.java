package bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import model.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "RequestBean")
@JsonRootName(value = "RequestBean")
@NoArgsConstructor
public class RequestBean {

    @JacksonXmlProperty(localName = "CorrelationId")
    @JsonProperty(value = "CorrelationId")
    private String correlationId;

    @JacksonXmlProperty(localName = "Destination")
    @JsonProperty(value = "Destination")
    private Constants destination;

    @JacksonXmlProperty(localName = "Message")
    @JsonProperty(value = "Message")
    private String message;

    /**
     *
     * @return RequestBean with properties
     */
    @Override
    public String toString() {
        return "RequestBean{" +
                "correlationId='" + this.correlationId + '\'' +
                ", destination='" + this.destination + '\'' +
                ", message='" + this.message + '\'' +
                '}';
    }
}
