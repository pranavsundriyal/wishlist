package com.wishlist.model.rule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;

/**
 * Created by psundriyal on 3/20/16.
 */

@JsonPropertyOrder({
        "criteriaType",
        "criteriaMap"
})
@JsonIgnoreProperties(ignoreUnknown = true)

public class Criteria {


    @JsonProperty("criteriaType")
    private String criteriaType;

    @JsonProperty("criteriaMap")
    private HashMap<String, String> criteriaMap;

    public String getCriteriaType() {
        return criteriaType;
    }

    public void setCriteriaType(String criteriaType) {
        this.criteriaType = criteriaType;
    }

    public HashMap<String, String> getCriteriaMap() {
        return criteriaMap;
    }

    public void setCriteriaMap(HashMap<String, String> criteriaMap) {
        this.criteriaMap = criteriaMap;
    }
}
