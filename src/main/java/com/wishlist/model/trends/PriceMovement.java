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
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "deltaMRLR",
        "delta7",
        "delta14",
        "delta21",
        "mostIncrease",
        "mostDecrease"
})
public class PriceMovement {

    @JsonProperty("deltaMRLR")
    private DeltaMRLR deltaMRLR;
    @JsonProperty("delta7")
    private Delta7 delta7;
    @JsonProperty("delta14")
    private Delta14 delta14;
    @JsonProperty("delta21")
    private Delta21 delta21;
    @JsonProperty("mostIncrease")
    private MostIncrease mostIncrease;
    @JsonProperty("mostDecrease")
    private MostDecrease mostDecrease;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The deltaMRLR
     */
    @JsonProperty("deltaMRLR")
    public DeltaMRLR getDeltaMRLR() {
        return deltaMRLR;
    }

    /**
     *
     * @param deltaMRLR
     * The deltaMRLR
     */
    @JsonProperty("deltaMRLR")
    public void setDeltaMRLR(DeltaMRLR deltaMRLR) {
        this.deltaMRLR = deltaMRLR;
    }

    /**
     *
     * @return
     * The delta7
     */
    @JsonProperty("delta7")
    public Delta7 getDelta7() {
        return delta7;
    }

    /**
     *
     * @param delta7
     * The delta7
     */
    @JsonProperty("delta7")
    public void setDelta7(Delta7 delta7) {
        this.delta7 = delta7;
    }

    /**
     *
     * @return
     * The delta14
     */
    @JsonProperty("delta14")
    public Delta14 getDelta14() {
        return delta14;
    }

    /**
     *
     * @param delta14
     * The delta14
     */
    @JsonProperty("delta14")
    public void setDelta14(Delta14 delta14) {
        this.delta14 = delta14;
    }

    /**
     *
     * @return
     * The delta21
     */
    @JsonProperty("delta21")
    public Delta21 getDelta21() {
        return delta21;
    }

    /**
     *
     * @param delta21
     * The delta21
     */
    @JsonProperty("delta21")
    public void setDelta21(Delta21 delta21) {
        this.delta21 = delta21;
    }

    /**
     *
     * @return
     * The mostIncrease
     */
    @JsonProperty("mostIncrease")
    public MostIncrease getMostIncrease() {
        return mostIncrease;
    }

    /**
     *
     * @param mostIncrease
     * The mostIncrease
     */
    @JsonProperty("mostIncrease")
    public void setMostIncrease(MostIncrease mostIncrease) {
        this.mostIncrease = mostIncrease;
    }

    /**
     *
     * @return
     * The mostDecrease
     */
    @JsonProperty("mostDecrease")
    public MostDecrease getMostDecrease() {
        return mostDecrease;
    }

    /**
     *
     * @param mostDecrease
     * The mostDecrease
     */
    @JsonProperty("mostDecrease")
    public void setMostDecrease(MostDecrease mostDecrease) {
        this.mostDecrease = mostDecrease;
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