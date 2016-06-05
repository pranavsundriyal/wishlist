package com.wishlist.model.trends;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "recommended"
})
public class TrendResponse {

    @JsonProperty("recommended")
    private Recommended recommended;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    private String lowestPrice;
    private String lastSeenPrice;

    /**
     *
     * @return
     * The recommended
     */
    @JsonProperty("recommended")
    public Recommended getRecommended() {
        return recommended;
    }

    /**
     *
     * @param recommended
     * The recommended
     */
    @JsonProperty("recommended")
    public void setRecommended(Recommended recommended) {
        this.recommended = recommended;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(String lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public String getLastSeenPrice() {
        return lastSeenPrice;
    }

    public void setLastSeenPrice(String lastSeenPrice) {
        this.lastSeenPrice = lastSeenPrice;
    }
}