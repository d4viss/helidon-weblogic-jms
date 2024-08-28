package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PayloadHolder {

    @JsonProperty("message")
    private String message;
    @JsonProperty("correlationId")
    private String correlationId;
    @JsonProperty("destination")
    private Constants destination;

    @Override
    public String toString() {
        return "PayloadHolder [payload=" + message + ", correlationId=" + correlationId + ", destination=" + destination + "]";
    }
}
