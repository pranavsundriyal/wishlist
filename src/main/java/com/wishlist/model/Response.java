package com.wishlist.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by psundriyal on 3/12/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response implements Serializable{

    @JsonProperty("legs")
    private List<Leg> legs = new ArrayList<Leg>();
    @JsonProperty("offers")
    private List<Offer> offers = new ArrayList<Offer>();
    @JsonProperty("errors")
    private List<Error> errors = new ArrayList<Error>();
    @JsonProperty("searchCities")
    private List<SearchCity> searchCities = new ArrayList<SearchCity>();
    @JsonProperty("obFeesDetails")
    private String obFeesDetails;
    @JsonProperty("activityId")
    private String activityId;
    @JsonProperty("percentDelaysCancellationsURL")
    private String percentDelaysCancellationsURL;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The legs
     */
    @JsonProperty("legs")
    public List<Leg> getLegs() {
        return legs;
    }

    /**
     *
     * @param legs
     * The legs
     */
    @JsonProperty("legs")
    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    /**
     *
     * @return
     * The offers
     */
    @JsonProperty("offers")
    public List<Offer> getOffers() {
        return offers;
    }

    /**
     *
     * @param offers
     * The offers
     */
    @JsonProperty("offers")
    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    /**
     *
     * @return
     * The errors
     */
    @JsonProperty("errors")
    public List<Error> getErrors() {
        return errors;
    }

    /**
     *
     * @param errors
     * The errors
     */
    @JsonProperty("errors")
    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    /**
     *
     * @return
     * The searchCities
     */
    @JsonProperty("searchCities")
    public List<SearchCity> getSearchCities() {
        return searchCities;
    }

    /**
     *
     * @param searchCities
     * The searchCities
     */
    @JsonProperty("searchCities")
    public void setSearchCities(List<SearchCity> searchCities) {
        this.searchCities = searchCities;
    }

    /**
     *
     * @return
     * The obFeesDetails
     */
    @JsonProperty("obFeesDetails")
    public String getObFeesDetails() {
        return obFeesDetails;
    }

    /**
     *
     * @param obFeesDetails
     * The obFeesDetails
     */
    @JsonProperty("obFeesDetails")
    public void setObFeesDetails(String obFeesDetails) {
        this.obFeesDetails = obFeesDetails;
    }

    /**
     *
     * @return
     * The activityId
     */
    @JsonProperty("activityId")
    public String getActivityId() {
        return activityId;
    }

    /**
     *
     * @param activityId
     * The activityId
     */
    @JsonProperty("activityId")
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    /**
     *
     * @return
     * The percentDelaysCancellationsURL
     */
    @JsonProperty("percentDelaysCancellationsURL")
    public String getPercentDelaysCancellationsURL() {
        return percentDelaysCancellationsURL;
    }

    /**
     *
     * @param percentDelaysCancellationsURL
     * The percentDelaysCancellationsURL
     */
    @JsonProperty("percentDelaysCancellationsURL")
    public void setPercentDelaysCancellationsURL(String percentDelaysCancellationsURL) {
        this.percentDelaysCancellationsURL = percentDelaysCancellationsURL;
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
