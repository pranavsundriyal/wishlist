package com.wishlist.model.trends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        "aggType",
        "departDate",
        "origin",
        "destination",
        "tpid",
        "travelClass",
        "tripType",
        "priceMovement",
        "trends",
        "predictionNextWeek"
})
public class Recommended {

    @JsonProperty("aggType")
    private String aggType;
    @JsonProperty("departDate")
    private String departDate;
    @JsonProperty("origin")
    private String origin;
    @JsonProperty("destination")
    private String destination;
    @JsonProperty("tpid")
    private Integer tpid;
    @JsonProperty("travelClass")
    private String travelClass;
    @JsonProperty("tripType")
    private String tripType;
    @JsonProperty("priceMovement")
    private PriceMovement priceMovement;
    @JsonProperty("trends")
    private List<Trend> trends = new ArrayList<Trend>();
    @JsonProperty("predictionNextWeek")
    private PredictionNextWeek predictionNextWeek;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The aggType
     */
    @JsonProperty("aggType")
    public String getAggType() {
        return aggType;
    }

    /**
     *
     * @param aggType
     * The aggType
     */
    @JsonProperty("aggType")
    public void setAggType(String aggType) {
        this.aggType = aggType;
    }

    /**
     *
     * @return
     * The departDate
     */
    @JsonProperty("departDate")
    public String getDepartDate() {
        return departDate;
    }

    /**
     *
     * @param departDate
     * The departDate
     */
    @JsonProperty("departDate")
    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    /**
     *
     * @return
     * The origin
     */
    @JsonProperty("origin")
    public String getOrigin() {
        return origin;
    }

    /**
     *
     * @param origin
     * The origin
     */
    @JsonProperty("origin")
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     *
     * @return
     * The destination
     */
    @JsonProperty("destination")
    public String getDestination() {
        return destination;
    }

    /**
     *
     * @param destination
     * The destination
     */
    @JsonProperty("destination")
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     *
     * @return
     * The tpid
     */
    @JsonProperty("tpid")
    public Integer getTpid() {
        return tpid;
    }

    /**
     *
     * @param tpid
     * The tpid
     */
    @JsonProperty("tpid")
    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    /**
     *
     * @return
     * The travelClass
     */
    @JsonProperty("travelClass")
    public String getTravelClass() {
        return travelClass;
    }

    /**
     *
     * @param travelClass
     * The travelClass
     */
    @JsonProperty("travelClass")
    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }

    /**
     *
     * @return
     * The tripType
     */
    @JsonProperty("tripType")
    public String getTripType() {
        return tripType;
    }

    /**
     *
     * @param tripType
     * The tripType
     */
    @JsonProperty("tripType")
    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    /**
     *
     * @return
     * The priceMovement
     */
    @JsonProperty("priceMovement")
    public PriceMovement getPriceMovement() {
        return priceMovement;
    }

    /**
     *
     * @param priceMovement
     * The priceMovement
     */
    @JsonProperty("priceMovement")
    public void setPriceMovement(PriceMovement priceMovement) {
        this.priceMovement = priceMovement;
    }

    /**
     *
     * @return
     * The trends
     */
    @JsonProperty("trends")
    public List<Trend> getTrends() {
        return trends;
    }

    /**
     *
     * @param trends
     * The trends
     */
    @JsonProperty("trends")
    public void setTrends(List<Trend> trends) {
        this.trends = trends;
    }

    /**
     *
     * @return
     * The predictionNextWeek
     */
    @JsonProperty("predictionNextWeek")
    public PredictionNextWeek getPredictionNextWeek() {
        return predictionNextWeek;
    }

    /**
     *
     * @param predictionNextWeek
     * The predictionNextWeek
     */
    @JsonProperty("predictionNextWeek")
    public void setPredictionNextWeek(PredictionNextWeek predictionNextWeek) {
        this.predictionNextWeek = predictionNextWeek;
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