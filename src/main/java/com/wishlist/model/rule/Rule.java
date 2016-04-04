package com.wishlist.model.rule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * Created by psundriyal on 3/20/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "email",
        "origin",
        "destination",
        "departurteDate",
        "arrivalDate",
        "flex",
        "filters"
})
public class Rule {

    @JsonProperty("email")
    private String email;
    @JsonProperty("arrivalDate")
    String arrivalDate;
    @JsonProperty("departurteDate")
    String departurteDate;
    @JsonProperty("origin")
    String origin;
    @JsonProperty("destination")
    String destination;
    @JsonProperty("flex")
    boolean flex;
    @JsonProperty("filters")
    private List<Filter> filters;

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDeparturteDate() {
        return departurteDate;
    }

    public void setDeparturteDate(String departurteDate) {
        this.departurteDate = departurteDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean getFlex() {
        return flex;
    }

    public void setFlex(boolean flex) {
        this.flex = flex;
    }
}
