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
        "searchDate",
        "min",
        "median",
        "max",
        "normalizedMin",
        "normalizedMedian",
        "normalizedMax",
        "sampleCount",
        "searchCount"
})
public class Trend {

    @JsonProperty("searchDate")
    private String searchDate;
    @JsonProperty("min")
    private String min;
    @JsonProperty("median")
    private String median;
    @JsonProperty("max")
    private String max;
    @JsonProperty("normalizedMin")
    private String normalizedMin;
    @JsonProperty("normalizedMedian")
    private String normalizedMedian;
    @JsonProperty("normalizedMax")
    private String normalizedMax;
    @JsonProperty("sampleCount")
    private Integer sampleCount;
    @JsonProperty("searchCount")
    private Integer searchCount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The searchDate
     */
    @JsonProperty("searchDate")
    public String getSearchDate() {
        return searchDate;
    }

    /**
     *
     * @param searchDate
     * The searchDate
     */
    @JsonProperty("searchDate")
    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

    /**
     *
     * @return
     * The min
     */
    @JsonProperty("min")
    public String getMin() {
        return min;
    }

    /**
     *
     * @param min
     * The min
     */
    @JsonProperty("min")
    public void setMin(String min) {
        this.min = min;
    }

    /**
     *
     * @return
     * The median
     */
    @JsonProperty("median")
    public String getMedian() {
        return median;
    }

    /**
     *
     * @param median
     * The median
     */
    @JsonProperty("median")
    public void setMedian(String median) {
        this.median = median;
    }

    /**
     *
     * @return
     * The max
     */
    @JsonProperty("max")
    public String getMax() {
        return max;
    }

    /**
     *
     * @param max
     * The max
     */
    @JsonProperty("max")
    public void setMax(String max) {
        this.max = max;
    }

    /**
     *
     * @return
     * The normalizedMin
     */
    @JsonProperty("normalizedMin")
    public String getNormalizedMin() {
        return normalizedMin;
    }

    /**
     *
     * @param normalizedMin
     * The normalizedMin
     */
    @JsonProperty("normalizedMin")
    public void setNormalizedMin(String normalizedMin) {
        this.normalizedMin = normalizedMin;
    }

    /**
     *
     * @return
     * The normalizedMedian
     */
    @JsonProperty("normalizedMedian")
    public String getNormalizedMedian() {
        return normalizedMedian;
    }

    /**
     *
     * @param normalizedMedian
     * The normalizedMedian
     */
    @JsonProperty("normalizedMedian")
    public void setNormalizedMedian(String normalizedMedian) {
        this.normalizedMedian = normalizedMedian;
    }

    /**
     *
     * @return
     * The normalizedMax
     */
    @JsonProperty("normalizedMax")
    public String getNormalizedMax() {
        return normalizedMax;
    }

    /**
     *
     * @param normalizedMax
     * The normalizedMax
     */
    @JsonProperty("normalizedMax")
    public void setNormalizedMax(String normalizedMax) {
        this.normalizedMax = normalizedMax;
    }

    /**
     *
     * @return
     * The sampleCount
     */
    @JsonProperty("sampleCount")
    public Integer getSampleCount() {
        return sampleCount;
    }

    /**
     *
     * @param sampleCount
     * The sampleCount
     */
    @JsonProperty("sampleCount")
    public void setSampleCount(Integer sampleCount) {
        this.sampleCount = sampleCount;
    }

    /**
     *
     * @return
     * The searchCount
     */
    @JsonProperty("searchCount")
    public Integer getSearchCount() {
        return searchCount;
    }

    /**
     *
     * @param searchCount
     * The searchCount
     */
    @JsonProperty("searchCount")
    public void setSearchCount(Integer searchCount) {
        this.searchCount = searchCount;
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