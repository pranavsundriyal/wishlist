package com.wishlist.model.trends;

/**
 * Created by psundriyal on 5/28/16.
 */

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
        "fromSearchDate",
        "toSearchDate",
        "numDays",
        "delta",
        "deltaPercent"
})
public class Delta14 {

    @JsonProperty("fromSearchDate")
    private String fromSearchDate;
    @JsonProperty("toSearchDate")
    private String toSearchDate;
    @JsonProperty("numDays")
    private Integer numDays;
    @JsonProperty("delta")
    private String delta;
    @JsonProperty("deltaPercent")
    private String deltaPercent;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The fromSearchDate
     */
    @JsonProperty("fromSearchDate")
    public String getFromSearchDate() {
        return fromSearchDate;
    }

    /**
     *
     * @param fromSearchDate
     * The fromSearchDate
     */
    @JsonProperty("fromSearchDate")
    public void setFromSearchDate(String fromSearchDate) {
        this.fromSearchDate = fromSearchDate;
    }

    /**
     *
     * @return
     * The toSearchDate
     */
    @JsonProperty("toSearchDate")
    public String getToSearchDate() {
        return toSearchDate;
    }

    /**
     *
     * @param toSearchDate
     * The toSearchDate
     */
    @JsonProperty("toSearchDate")
    public void setToSearchDate(String toSearchDate) {
        this.toSearchDate = toSearchDate;
    }

    /**
     *
     * @return
     * The numDays
     */
    @JsonProperty("numDays")
    public Integer getNumDays() {
        return numDays;
    }

    /**
     *
     * @param numDays
     * The numDays
     */
    @JsonProperty("numDays")
    public void setNumDays(Integer numDays) {
        this.numDays = numDays;
    }

    /**
     *
     * @return
     * The delta
     */
    @JsonProperty("delta")
    public String getDelta() {
        return delta;
    }

    /**
     *
     * @param delta
     * The delta
     */
    @JsonProperty("delta")
    public void setDelta(String delta) {
        this.delta = delta;
    }

    /**
     *
     * @return
     * The deltaPercent
     */
    @JsonProperty("deltaPercent")
    public String getDeltaPercent() {
        return deltaPercent;
    }

    /**
     *
     * @param deltaPercent
     * The deltaPercent
     */
    @JsonProperty("deltaPercent")
    public void setDeltaPercent(String deltaPercent) {
        this.deltaPercent = deltaPercent;
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