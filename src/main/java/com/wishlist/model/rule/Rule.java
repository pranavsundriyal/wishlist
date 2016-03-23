package com.wishlist.model.rule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * Created by psundriyal on 3/20/16.
 */

@JsonPropertyOrder({
        "email",
        "criterias"
})
public class Rule {

    @JsonProperty("email")
    private String email;

    @JsonProperty("criterias")
    private List<Criteria> criterias;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(List<Criteria> criterias) {
        this.criterias = criterias;
    }
}
