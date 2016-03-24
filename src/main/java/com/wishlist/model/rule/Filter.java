package com.wishlist.model.rule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;

/**
 * Created by psundriyal on 3/20/16.
 */
@JsonPropertyOrder({
        "filterType",
        "filterMap"
})
@JsonIgnoreProperties(ignoreUnknown = true)

public class Filter {


    @JsonProperty("filterType")
    private String filterType;

    @JsonProperty("filterMap")
    private HashMap<String, String> filterMap;

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public HashMap<String, String> getFilterMap() {
        return filterMap;
    }

    public void setFilterMap(HashMap<String, String> filterMap) {
        this.filterMap = filterMap;
    }
}
