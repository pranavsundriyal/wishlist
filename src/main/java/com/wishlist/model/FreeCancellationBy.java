package com.wishlist.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "raw",
        "localized",
        "epochSeconds",
        "timeZoneOffsetSeconds",
        "localizedShortDate"
})
@JsonIgnoreProperties(ignoreUnknown = true)

public class FreeCancellationBy implements Serializable{

    @JsonProperty("raw")
    private String raw;
    @JsonProperty("localized")
    private String localized;
    @JsonProperty("epochSeconds")
    private String epochSeconds;
    @JsonProperty("timeZoneOffsetSeconds")
    private String timeZoneOffsetSeconds;
    @JsonProperty("localizedShortDate")
    private String localizedShortDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The raw
     */
    @JsonProperty("raw")
    public String getRaw() {
        return raw;
    }

    /**
     *
     * @param raw
     * The raw
     */
    @JsonProperty("raw")
    public void setRaw(String raw) {
        this.raw = raw;
    }

    /**
     *
     * @return
     * The localized
     */
    @JsonProperty("localized")
    public String getLocalized() {
        return localized;
    }

    /**
     *
     * @param localized
     * The localized
     */
    @JsonProperty("localized")
    public void setLocalized(String localized) {
        this.localized = localized;
    }

    /**
     *
     * @return
     * The epochSeconds
     */
    @JsonProperty("epochSeconds")
    public String getEpochSeconds() {
        return epochSeconds;
    }

    /**
     *
     * @param epochSeconds
     * The epochSeconds
     */
    @JsonProperty("epochSeconds")
    public void setEpochSeconds(String epochSeconds) {
        this.epochSeconds = epochSeconds;
    }

    /**
     *
     * @return
     * The timeZoneOffsetSeconds
     */
    @JsonProperty("timeZoneOffsetSeconds")
    public String getTimeZoneOffsetSeconds() {
        return timeZoneOffsetSeconds;
    }

    /**
     *
     * @param timeZoneOffsetSeconds
     * The timeZoneOffsetSeconds
     */
    @JsonProperty("timeZoneOffsetSeconds")
    public void setTimeZoneOffsetSeconds(String timeZoneOffsetSeconds) {
        this.timeZoneOffsetSeconds = timeZoneOffsetSeconds;
    }

    /**
     *
     * @return
     * The localizedShortDate
     */
    @JsonProperty("localizedShortDate")
    public String getLocalizedShortDate() {
        return localizedShortDate;
    }

    /**
     *
     * @param localizedShortDate
     * The localizedShortDate
     */
    @JsonProperty("localizedShortDate")
    public void setLocalizedShortDate(String localizedShortDate) {
        this.localizedShortDate = localizedShortDate;
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