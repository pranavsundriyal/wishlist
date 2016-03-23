package com.wishlist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonPropertyOrder({
        "departureTime",
        "departureTimeEpochSeconds",
        "departureTimeRaw",
        "departureTimeZoneOffsetSeconds",
        "arrivalTime",
        "arrivalTimeEpochSeconds",
        "arrivalTimeRaw",
        "arrivalTimeZoneOffsetSeconds",
        "arrivalAirportCode",
        "arrivalAirportLocation",
        "departureAirportCode",
        "departureAirportLocation",
        "airlineName",
        "airlineCode",
        "flightNumber",
        "onTimePercentage",
        "operatingAirlineName",
        "operatingAirlineCode",
        "equipmentCode",
        "equipmentDescription",
        "duration",
        "distance",
        "distanceUnits",
        "stops",
        "meal",
        "sameFlightAsPreviousSegment",
        "providerCode"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Segment {

    @JsonProperty("departureTime")
    private String departureTime;
    @JsonProperty("departureTimeEpochSeconds")
    private Integer departureTimeEpochSeconds;
    @JsonProperty("departureTimeRaw")
    private String departureTimeRaw;
    @JsonProperty("departureTimeZoneOffsetSeconds")
    private Integer departureTimeZoneOffsetSeconds;
    @JsonProperty("arrivalTime")
    private String arrivalTime;
    @JsonProperty("arrivalTimeEpochSeconds")
    private Integer arrivalTimeEpochSeconds;
    @JsonProperty("arrivalTimeRaw")
    private String arrivalTimeRaw;
    @JsonProperty("arrivalTimeZoneOffsetSeconds")
    private Integer arrivalTimeZoneOffsetSeconds;
    @JsonProperty("arrivalAirportCode")
    private String arrivalAirportCode;
    @JsonProperty("arrivalAirportLocation")
    private String arrivalAirportLocation;
    @JsonProperty("departureAirportCode")
    private String departureAirportCode;
    @JsonProperty("departureAirportLocation")
    private String departureAirportLocation;
    @JsonProperty("airlineName")
    private String airlineName;
    @JsonProperty("airlineCode")
    private String airlineCode;
    @JsonProperty("flightNumber")
    private String flightNumber;
    @JsonProperty("onTimePercentage")
    private String onTimePercentage;
    @JsonProperty("operatingAirlineName")
    private String operatingAirlineName;
    @JsonProperty("operatingAirlineCode")
    private String operatingAirlineCode;
    @JsonProperty("equipmentCode")
    private String equipmentCode;
    @JsonProperty("equipmentDescription")
    private String equipmentDescription;
    @JsonProperty("duration")
    private String duration;
    @JsonProperty("distance")
    private Integer distance;
    @JsonProperty("distanceUnits")
    private String distanceUnits;
    @JsonProperty("stops")
    private Integer stops;
    @JsonProperty("meal")
    private String meal;
    @JsonProperty("sameFlightAsPreviousSegment")
    private Boolean sameFlightAsPreviousSegment;
    @JsonProperty("providerCode")
    private String providerCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The departureTime
     */
    @JsonProperty("departureTime")
    public String getDepartureTime() {
        return departureTime;
    }

    /**
     *
     * @param departureTime
     * The departureTime
     */
    @JsonProperty("departureTime")
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    /**
     *
     * @return
     * The departureTimeEpochSeconds
     */
    @JsonProperty("departureTimeEpochSeconds")
    public Integer getDepartureTimeEpochSeconds() {
        return departureTimeEpochSeconds;
    }

    /**
     *
     * @param departureTimeEpochSeconds
     * The departureTimeEpochSeconds
     */
    @JsonProperty("departureTimeEpochSeconds")
    public void setDepartureTimeEpochSeconds(Integer departureTimeEpochSeconds) {
        this.departureTimeEpochSeconds = departureTimeEpochSeconds;
    }

    /**
     *
     * @return
     * The departureTimeRaw
     */
    @JsonProperty("departureTimeRaw")
    public String getDepartureTimeRaw() {
        return departureTimeRaw;
    }

    /**
     *
     * @param departureTimeRaw
     * The departureTimeRaw
     */
    @JsonProperty("departureTimeRaw")
    public void setDepartureTimeRaw(String departureTimeRaw) {
        this.departureTimeRaw = departureTimeRaw;
    }

    /**
     *
     * @return
     * The departureTimeZoneOffsetSeconds
     */
    @JsonProperty("departureTimeZoneOffsetSeconds")
    public Integer getDepartureTimeZoneOffsetSeconds() {
        return departureTimeZoneOffsetSeconds;
    }

    /**
     *
     * @param departureTimeZoneOffsetSeconds
     * The departureTimeZoneOffsetSeconds
     */
    @JsonProperty("departureTimeZoneOffsetSeconds")
    public void setDepartureTimeZoneOffsetSeconds(Integer departureTimeZoneOffsetSeconds) {
        this.departureTimeZoneOffsetSeconds = departureTimeZoneOffsetSeconds;
    }

    /**
     *
     * @return
     * The arrivalTime
     */
    @JsonProperty("arrivalTime")
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     *
     * @param arrivalTime
     * The arrivalTime
     */
    @JsonProperty("arrivalTime")
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     *
     * @return
     * The arrivalTimeEpochSeconds
     */
    @JsonProperty("arrivalTimeEpochSeconds")
    public Integer getArrivalTimeEpochSeconds() {
        return arrivalTimeEpochSeconds;
    }

    /**
     *
     * @param arrivalTimeEpochSeconds
     * The arrivalTimeEpochSeconds
     */
    @JsonProperty("arrivalTimeEpochSeconds")
    public void setArrivalTimeEpochSeconds(Integer arrivalTimeEpochSeconds) {
        this.arrivalTimeEpochSeconds = arrivalTimeEpochSeconds;
    }

    /**
     *
     * @return
     * The arrivalTimeRaw
     */
    @JsonProperty("arrivalTimeRaw")
    public String getArrivalTimeRaw() {
        return arrivalTimeRaw;
    }

    /**
     *
     * @param arrivalTimeRaw
     * The arrivalTimeRaw
     */
    @JsonProperty("arrivalTimeRaw")
    public void setArrivalTimeRaw(String arrivalTimeRaw) {
        this.arrivalTimeRaw = arrivalTimeRaw;
    }

    /**
     *
     * @return
     * The arrivalTimeZoneOffsetSeconds
     */
    @JsonProperty("arrivalTimeZoneOffsetSeconds")
    public Integer getArrivalTimeZoneOffsetSeconds() {
        return arrivalTimeZoneOffsetSeconds;
    }

    /**
     *
     * @param arrivalTimeZoneOffsetSeconds
     * The arrivalTimeZoneOffsetSeconds
     */
    @JsonProperty("arrivalTimeZoneOffsetSeconds")
    public void setArrivalTimeZoneOffsetSeconds(Integer arrivalTimeZoneOffsetSeconds) {
        this.arrivalTimeZoneOffsetSeconds = arrivalTimeZoneOffsetSeconds;
    }

    /**
     *
     * @return
     * The arrivalAirportCode
     */
    @JsonProperty("arrivalAirportCode")
    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    /**
     *
     * @param arrivalAirportCode
     * The arrivalAirportCode
     */
    @JsonProperty("arrivalAirportCode")
    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    /**
     *
     * @return
     * The arrivalAirportLocation
     */
    @JsonProperty("arrivalAirportLocation")
    public String getArrivalAirportLocation() {
        return arrivalAirportLocation;
    }

    /**
     *
     * @param arrivalAirportLocation
     * The arrivalAirportLocation
     */
    @JsonProperty("arrivalAirportLocation")
    public void setArrivalAirportLocation(String arrivalAirportLocation) {
        this.arrivalAirportLocation = arrivalAirportLocation;
    }

    /**
     *
     * @return
     * The departureAirportCode
     */
    @JsonProperty("departureAirportCode")
    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    /**
     *
     * @param departureAirportCode
     * The departureAirportCode
     */
    @JsonProperty("departureAirportCode")
    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    /**
     *
     * @return
     * The departureAirportLocation
     */
    @JsonProperty("departureAirportLocation")
    public String getDepartureAirportLocation() {
        return departureAirportLocation;
    }

    /**
     *
     * @param departureAirportLocation
     * The departureAirportLocation
     */
    @JsonProperty("departureAirportLocation")
    public void setDepartureAirportLocation(String departureAirportLocation) {
        this.departureAirportLocation = departureAirportLocation;
    }

    /**
     *
     * @return
     * The airlineName
     */
    @JsonProperty("airlineName")
    public String getAirlineName() {
        return airlineName;
    }

    /**
     *
     * @param airlineName
     * The airlineName
     */
    @JsonProperty("airlineName")
    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    /**
     *
     * @return
     * The airlineCode
     */
    @JsonProperty("airlineCode")
    public String getAirlineCode() {
        return airlineCode;
    }

    /**
     *
     * @param airlineCode
     * The airlineCode
     */
    @JsonProperty("airlineCode")
    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    /**
     *
     * @return
     * The flightNumber
     */
    @JsonProperty("flightNumber")
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     *
     * @param flightNumber
     * The flightNumber
     */
    @JsonProperty("flightNumber")
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     *
     * @return
     * The onTimePercentage
     */
    @JsonProperty("onTimePercentage")
    public String getOnTimePercentage() {
        return onTimePercentage;
    }

    /**
     *
     * @param onTimePercentage
     * The onTimePercentage
     */
    @JsonProperty("onTimePercentage")
    public void setOnTimePercentage(String onTimePercentage) {
        this.onTimePercentage = onTimePercentage;
    }

    /**
     *
     * @return
     * The operatingAirlineName
     */
    @JsonProperty("operatingAirlineName")
    public String getOperatingAirlineName() {
        return operatingAirlineName;
    }

    /**
     *
     * @param operatingAirlineName
     * The operatingAirlineName
     */
    @JsonProperty("operatingAirlineName")
    public void setOperatingAirlineName(String operatingAirlineName) {
        this.operatingAirlineName = operatingAirlineName;
    }

    /**
     *
     * @return
     * The operatingAirlineCode
     */
    @JsonProperty("operatingAirlineCode")
    public String getOperatingAirlineCode() {
        return operatingAirlineCode;
    }

    /**
     *
     * @param operatingAirlineCode
     * The operatingAirlineCode
     */
    @JsonProperty("operatingAirlineCode")
    public void setOperatingAirlineCode(String operatingAirlineCode) {
        this.operatingAirlineCode = operatingAirlineCode;
    }

    /**
     *
     * @return
     * The equipmentCode
     */
    @JsonProperty("equipmentCode")
    public String getEquipmentCode() {
        return equipmentCode;
    }

    /**
     *
     * @param equipmentCode
     * The equipmentCode
     */
    @JsonProperty("equipmentCode")
    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    /**
     *
     * @return
     * The equipmentDescription
     */
    @JsonProperty("equipmentDescription")
    public String getEquipmentDescription() {
        return equipmentDescription;
    }

    /**
     *
     * @param equipmentDescription
     * The equipmentDescription
     */
    @JsonProperty("equipmentDescription")
    public void setEquipmentDescription(String equipmentDescription) {
        this.equipmentDescription = equipmentDescription;
    }

    /**
     *
     * @return
     * The duration
     */
    @JsonProperty("duration")
    public String getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     * The duration
     */
    @JsonProperty("duration")
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     *
     * @return
     * The distance
     */
    @JsonProperty("distance")
    public Integer getDistance() {
        return distance;
    }

    /**
     *
     * @param distance
     * The distance
     */
    @JsonProperty("distance")
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    /**
     *
     * @return
     * The distanceUnits
     */
    @JsonProperty("distanceUnits")
    public String getDistanceUnits() {
        return distanceUnits;
    }

    /**
     *
     * @param distanceUnits
     * The distanceUnits
     */
    @JsonProperty("distanceUnits")
    public void setDistanceUnits(String distanceUnits) {
        this.distanceUnits = distanceUnits;
    }

    /**
     *
     * @return
     * The stops
     */
    @JsonProperty("stops")
    public Integer getStops() {
        return stops;
    }

    /**
     *
     * @param stops
     * The stops
     */
    @JsonProperty("stops")
    public void setStops(Integer stops) {
        this.stops = stops;
    }

    /**
     *
     * @return
     * The meal
     */
    @JsonProperty("meal")
    public String getMeal() {
        return meal;
    }

    /**
     *
     * @param meal
     * The meal
     */
    @JsonProperty("meal")
    public void setMeal(String meal) {
        this.meal = meal;
    }

    /**
     *
     * @return
     * The sameFlightAsPreviousSegment
     */
    @JsonProperty("sameFlightAsPreviousSegment")
    public Boolean getSameFlightAsPreviousSegment() {
        return sameFlightAsPreviousSegment;
    }

    /**
     *
     * @param sameFlightAsPreviousSegment
     * The sameFlightAsPreviousSegment
     */
    @JsonProperty("sameFlightAsPreviousSegment")
    public void setSameFlightAsPreviousSegment(Boolean sameFlightAsPreviousSegment) {
        this.sameFlightAsPreviousSegment = sameFlightAsPreviousSegment;
    }

    /**
     *
     * @return
     * The providerCode
     */
    @JsonProperty("providerCode")
    public String getProviderCode() {
        return providerCode;
    }

    /**
     *
     * @param providerCode
     * The providerCode
     */
    @JsonProperty("providerCode")
    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
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