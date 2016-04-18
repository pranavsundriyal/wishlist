package com.wishlist.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "passengerCategory"
})@JsonIgnoreProperties(ignoreUnknown = true)
public class PricePerPassengerCategory implements Serializable{

    @JsonProperty("passengerCategory")
    private String passengerCategory;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The passengerCategory
     */
    @JsonProperty("passengerCategory")
    public String getPassengerCategory() {
        return passengerCategory;
    }

    /**
     *
     * @param passengerCategory
     * The passengerCategory
     */
    @JsonProperty("passengerCategory")
    public void setPassengerCategory(String passengerCategory) {
        this.passengerCategory = passengerCategory;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}