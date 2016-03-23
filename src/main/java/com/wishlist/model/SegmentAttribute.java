package com.wishlist.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SegmentAttribute {

    @JsonProperty("bookingCode")
    private String bookingCode;
    @JsonProperty("cabinCode")
    private String cabinCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The bookingCode
     */
    @JsonProperty("bookingCode")
    public String getBookingCode() {
        return bookingCode;
    }

    /**
     *
     * @param bookingCode
     * The bookingCode
     */
    @JsonProperty("bookingCode")
    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    /**
     *
     * @return
     * The cabinCode
     */
    @JsonProperty("cabinCode")
    public String getCabinCode() {
        return cabinCode;
    }

    /**
     *
     * @param cabinCode
     * The cabinCode
     */
    @JsonProperty("cabinCode")
    public void setCabinCode(String cabinCode) {
        this.cabinCode = cabinCode;
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