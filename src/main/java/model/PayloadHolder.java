package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PayloadHolder {

    @JsonProperty("payload")
    private String payload;
    @JsonProperty("correlationId")
    private String correlationId;
    @JsonProperty("destination")
    private Constants destination;

    @Override
    public String toString() {
        return "PayloadHolder [payload=" + payload + ", correlationId=" + correlationId + ", destination=" + destination + "]";
    }
}
