package com.wishlist.model;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "amount",
        "formattedPrice",
        "formattedWholePrice"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseFarePrice {

    @JsonProperty("amount")
    private String amount;
    @JsonProperty("formattedPrice")
    private String formattedPrice;
    @JsonProperty("formattedWholePrice")
    private String formattedWholePrice;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The amount
     */
    @JsonProperty("amount")
    public String getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     * The amount
     */
    @JsonProperty("amount")
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     * The formattedPrice
     */
    @JsonProperty("formattedPrice")
    public String getFormattedPrice() {
        return formattedPrice;
    }

    /**
     *
     * @param formattedPrice
     * The formattedPrice
     */
    @JsonProperty("formattedPrice")
    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    /**
     *
     * @return
     * The formattedWholePrice
     */
    @JsonProperty("formattedWholePrice")
    public String getFormattedWholePrice() {
        return formattedWholePrice;
    }

    /**
     *
     * @param formattedWholePrice
     * The formattedWholePrice
     */
    @JsonProperty("formattedWholePrice")
    public void setFormattedWholePrice(String formattedWholePrice) {
        this.formattedWholePrice = formattedWholePrice;
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