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
        "prediction",
        "confidence",
        "departWeek",
        "apWeek"
})
public class PredictionNextWeek {

    @JsonProperty("prediction")
    private Double prediction;
    @JsonProperty("confidence")
    private Double confidence;
    @JsonProperty("departWeek")
    private String departWeek;
    @JsonProperty("apWeek")
    private Integer apWeek;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The prediction
     */
    @JsonProperty("prediction")
    public Double getPrediction() {
        return prediction;
    }

    /**
     *
     * @param prediction
     * The prediction
     */
    @JsonProperty("prediction")
    public void setPrediction(Double prediction) {
        this.prediction = prediction;
    }

    /**
     *
     * @return
     * The confidence
     */
    @JsonProperty("confidence")
    public Double getConfidence() {
        return confidence;
    }

    /**
     *
     * @param confidence
     * The confidence
     */
    @JsonProperty("confidence")
    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    /**
     *
     * @return
     * The departWeek
     */
    @JsonProperty("departWeek")
    public String getDepartWeek() {
        return departWeek;
    }

    /**
     *
     * @param departWeek
     * The departWeek
     */
    @JsonProperty("departWeek")
    public void setDepartWeek(String departWeek) {
        this.departWeek = departWeek;
    }

    /**
     *
     * @return
     * The apWeek
     */
    @JsonProperty("apWeek")
    public Integer getApWeek() {
        return apWeek;
    }

    /**
     *
     * @param apWeek
     * The apWeek
     */
    @JsonProperty("apWeek")
    public void setApWeek(Integer apWeek) {
        this.apWeek = apWeek;
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