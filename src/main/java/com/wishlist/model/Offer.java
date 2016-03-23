package com.wishlist.model;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by psundriyal on 3/12/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "legIds",
        "currency",
        "baseFare",
        "baseFarePrice",
        "totalFare",
        "pricePerPassengerCategory",
        "numberOfTickets",
        "taxes",
        "fees",
        "showFees",
        "productKey",
        "mobileShoppingKey",
        "seatsRemaining",
        "segmentAttributes",
        "baggageFeesUrl",
        "fareType",
        "fareName",
        "fareNameLink",
        "isInternational",
        "mayChargeOBFees",
        "hasBagFee",
        "hasNoBagFee"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Offer {

    @JsonProperty("legIds")
    private List<String> legIds = new ArrayList<String>();
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("baseFare")
    private String baseFare;
    @JsonProperty("baseFarePrice")
    private BaseFarePrice baseFarePrice;
    @JsonProperty("totalFare")
    private String totalFare;
    @JsonProperty("pricePerPassengerCategory")
    private List<PricePerPassengerCategory> pricePerPassengerCategory = new ArrayList<PricePerPassengerCategory>();
    @JsonProperty("numberOfTickets")
    private Integer numberOfTickets;
    @JsonProperty("taxes")
    private String taxes;
    @JsonProperty("fees")
    private String fees;
    @JsonProperty("showFees")
    private Boolean showFees;
    @JsonProperty("productKey")
    private String productKey;
    @JsonProperty("mobileShoppingKey")
    private String mobileShoppingKey;
    @JsonProperty("seatsRemaining")
    private Integer seatsRemaining;
    /*@JsonProperty("segmentAttributes")
    private List<SegmentAttribute> segmentAttributes = new ArrayList<SegmentAttribute>();*/
    @JsonProperty("baggageFeesUrl")
    private String baggageFeesUrl;
    @JsonProperty("fareType")
    private String fareType;
    @JsonProperty("fareName")
    private String fareName;
    @JsonProperty("fareNameLink")
    private String fareNameLink;
    @JsonProperty("isInternational")
    private Boolean isInternational;
    @JsonProperty("mayChargeOBFees")
    private Boolean mayChargeOBFees;
    @JsonProperty("hasBagFee")
    private Boolean hasBagFee;
    @JsonProperty("hasNoBagFee")
    private Boolean hasNoBagFee;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The legIds
     */
    @JsonProperty("legIds")
    public List<String> getLegIds() {
        return legIds;
    }

    /**
     * @param legIds The legIds
     */
    @JsonProperty("legIds")
    public void setLegIds(List<String> legIds) {
        this.legIds = legIds;
    }

    /**
     * @return The currency
     */
    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency The currency
     */
    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return The baseFare
     */
    @JsonProperty("baseFare")
    public String getBaseFare() {
        return baseFare;
    }

    /**
     * @param baseFare The baseFare
     */
    @JsonProperty("baseFare")
    public void setBaseFare(String baseFare) {
        this.baseFare = baseFare;
    }

    /**
     * @return The baseFarePrice
     */
    @JsonProperty("baseFarePrice")
    public BaseFarePrice getBaseFarePrice() {
        return baseFarePrice;
    }

    /**
     * @param baseFarePrice The baseFarePrice
     */
    @JsonProperty("baseFarePrice")
    public void setBaseFarePrice(BaseFarePrice baseFarePrice) {
        this.baseFarePrice = baseFarePrice;
    }

    /**
     * @return The totalFare
     */
    @JsonProperty("totalFare")
    public String getTotalFare() {
        return totalFare;
    }

    /**
     * @param totalFare The totalFare
     */
    @JsonProperty("totalFare")
    public void setTotalFare(String totalFare) {
        this.totalFare = totalFare;
    }

    /**
     * @return The pricePerPassengerCategory
     */
    @JsonProperty("pricePerPassengerCategory")
    public List<PricePerPassengerCategory> getPricePerPassengerCategory() {
        return pricePerPassengerCategory;
    }

    /**
     * @param pricePerPassengerCategory The pricePerPassengerCategory
     */
    @JsonProperty("pricePerPassengerCategory")
    public void setPricePerPassengerCategory(List<PricePerPassengerCategory> pricePerPassengerCategory) {
        this.pricePerPassengerCategory = pricePerPassengerCategory;
    }

    /**
     * @return The numberOfTickets
     */
    @JsonProperty("numberOfTickets")
    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    /**
     * @param numberOfTickets The numberOfTickets
     */
    @JsonProperty("numberOfTickets")
    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    /**
     * @return The taxes
     */
    @JsonProperty("taxes")
    public String getTaxes() {
        return taxes;
    }

    /**
     * @param taxes The taxes
     */
    @JsonProperty("taxes")
    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }

    /**
     * @return The fees
     */
    @JsonProperty("fees")
    public String getFees() {
        return fees;
    }

    /**
     * @param fees The fees
     */
    @JsonProperty("fees")
    public void setFees(String fees) {
        this.fees = fees;
    }

    /**
     * @return The showFees
     */
    @JsonProperty("showFees")
    public Boolean getShowFees() {
        return showFees;
    }

    /**
     * @param showFees The showFees
     */
    @JsonProperty("showFees")
    public void setShowFees(Boolean showFees) {
        this.showFees = showFees;
    }

    /**
     * @return The productKey
     */
    @JsonProperty("productKey")
    public String getProductKey() {
        return productKey;
    }

    /**
     * @param productKey The productKey
     */
    @JsonProperty("productKey")
    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    /**
     * @return The mobileShoppingKey
     */
    @JsonProperty("mobileShoppingKey")
    public String getMobileShoppingKey() {
        return mobileShoppingKey;
    }

    /**
     * @param mobileShoppingKey The mobileShoppingKey
     */
    @JsonProperty("mobileShoppingKey")
    public void setMobileShoppingKey(String mobileShoppingKey) {
        this.mobileShoppingKey = mobileShoppingKey;
    }

    /**
     * @return The seatsRemaining
     */
    @JsonProperty("seatsRemaining")
    public Integer getSeatsRemaining() {
        return seatsRemaining;
    }

    /**
     * @param seatsRemaining The seatsRemaining
     */
    @JsonProperty("seatsRemaining")
    public void setSeatsRemaining(Integer seatsRemaining) {
        this.seatsRemaining = seatsRemaining;
    }

    /**
     * @return The segmentAttributes
     */
   /* @JsonProperty("segmentAttributes")
    public List<SegmentAttribute> getSegmentAttributes() {
        return segmentAttributes;
    }

    *//**
     * @param segmentAttributes The segmentAttributes
     *//*
    @JsonProperty("segmentAttributes")
    public void setSegmentAttributes(List<SegmentAttribute> segmentAttributes) {
        this.segmentAttributes = segmentAttributes;
    }*/

    /**
     * @return The baggageFeesUrl
     */
    @JsonProperty("baggageFeesUrl")
    public String getBaggageFeesUrl() {
        return baggageFeesUrl;
    }

    /**
     * @param baggageFeesUrl The baggageFeesUrl
     */
    @JsonProperty("baggageFeesUrl")
    public void setBaggageFeesUrl(String baggageFeesUrl) {
        this.baggageFeesUrl = baggageFeesUrl;
    }

    /**
     * @return The fareType
     */
    @JsonProperty("fareType")
    public String getFareType() {
        return fareType;
    }

    /**
     * @param fareType The fareType
     */
    @JsonProperty("fareType")
    public void setFareType(String fareType) {
        this.fareType = fareType;
    }

    /**
     * @return The fareName
     */
    @JsonProperty("fareName")
    public String getFareName() {
        return fareName;
    }

    /**
     * @param fareName The fareName
     */
    @JsonProperty("fareName")
    public void setFareName(String fareName) {
        this.fareName = fareName;
    }

    /**
     * @return The fareNameLink
     */
    @JsonProperty("fareNameLink")
    public String getFareNameLink() {
        return fareNameLink;
    }

    /**
     * @param fareNameLink The fareNameLink
     */
    @JsonProperty("fareNameLink")
    public void setFareNameLink(String fareNameLink) {
        this.fareNameLink = fareNameLink;
    }

    /**
     * @return The isInternational
     */
    @JsonProperty("isInternational")
    public Boolean getIsInternational() {
        return isInternational;
    }

    /**
     * @param isInternational The isInternational
     */
    @JsonProperty("isInternational")
    public void setIsInternational(Boolean isInternational) {
        this.isInternational = isInternational;
    }

    /**
     * @return The mayChargeOBFees
     */
    @JsonProperty("mayChargeOBFees")
    public Boolean getMayChargeOBFees() {
        return mayChargeOBFees;
    }

    /**
     * @param mayChargeOBFees The mayChargeOBFees
     */
    @JsonProperty("mayChargeOBFees")
    public void setMayChargeOBFees(Boolean mayChargeOBFees) {
        this.mayChargeOBFees = mayChargeOBFees;
    }

    /**
     * @return The hasBagFee
     */
    @JsonProperty("hasBagFee")
    public Boolean getHasBagFee() {
        return hasBagFee;
    }

    /**
     * @param hasBagFee The hasBagFee
     */
    @JsonProperty("hasBagFee")
    public void setHasBagFee(Boolean hasBagFee) {
        this.hasBagFee = hasBagFee;
    }

    /**
     * @return The hasNoBagFee
     */
    @JsonProperty("hasNoBagFee")
    public Boolean getHasNoBagFee() {
        return hasNoBagFee;
    }

    /**
     * @param hasNoBagFee The hasNoBagFee
     */
    @JsonProperty("hasNoBagFee")
    public void setHasNoBagFee(Boolean hasNoBagFee) {
        this.hasNoBagFee = hasNoBagFee;
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
