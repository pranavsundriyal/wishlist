package com.wishlist.model;

import com.fasterxml.jackson.annotation.*;
import com.wishlist.model.slim.Time;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by psundriyal on 3/10/16.
 */



@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
            "legId",
            "baggageFeesUrl",
            "segments",
            "freeCancellationBy"
    })
@JsonIgnoreProperties(ignoreUnknown = true)
public class Leg implements Serializable{

        @JsonProperty("legId")
        private String legId;
        @JsonProperty("baggageFeesUrl")
        private String baggageFeesUrl;
        @JsonProperty("segments")
        private List<Segment> segments = new ArrayList<Segment>();
        @JsonProperty("freeCancellationBy")
        private FreeCancellationBy freeCancellationBy;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        private LocalDateTime arrivalTime;

        private LocalDateTime departureTime;

        private Time duration;

        private Time layover;

        private Time totalDuration;
        /**
         *
         * @return
         * The legId
         */
        @JsonProperty("legId")
        public String getLegId() {
            return legId;
        }

        /**
         *
         * @param legId
         * The legId
         */
        @JsonProperty("legId")
        public void setLegId(String legId) {
            this.legId = legId;
        }

        /**
         *
         * @return
         * The baggageFeesUrl
         */
        @JsonProperty("baggageFeesUrl")
        public String getBaggageFeesUrl() {
            return baggageFeesUrl;
        }

        /**
         *
         * @param baggageFeesUrl
         * The baggageFeesUrl
         */
        @JsonProperty("baggageFeesUrl")
        public void setBaggageFeesUrl(String baggageFeesUrl) {
            this.baggageFeesUrl = baggageFeesUrl;
        }

        /**
         *
         * @return
         * The segments
         */
        @JsonProperty("segments")
        public List<Segment> getSegments() {
            return segments;
        }

        /**
         *
         * @param segments
         * The segments
         */
        @JsonProperty("segments")
        public void setSegments(List<Segment> segments) {
            this.segments = segments;
        }

        /**
         *
         * @return
         * The freeCancellationBy
         */
        @JsonProperty("freeCancellationBy")
        public FreeCancellationBy getFreeCancellationBy() {
            return freeCancellationBy;
        }

        /**
         *
         * @param freeCancellationBy
         * The freeCancellationBy
         */
        @JsonProperty("freeCancellationBy")
        public void setFreeCancellationBy(FreeCancellationBy freeCancellationBy) {
            this.freeCancellationBy = freeCancellationBy;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }


        public LocalDateTime getArrivalTime() {
                return arrivalTime;
        }

        public void setArrivalTime(LocalDateTime arrivalTime) {
                this.arrivalTime = arrivalTime;
        }

        public LocalDateTime getDepartureTime() {
                return departureTime;
        }

        public void setDepartureTime(LocalDateTime departureTime) {
                this.departureTime = departureTime;
        }

        public Time getDuration() {
                return duration;
        }

        public void setDuration(Time duration) {
                this.duration = duration;
        }

        public Time getLayover() {
                return layover;
        }

        public void setLayover(Time layover) {
                this.layover = layover;
        }

        public Time getTotalDuration() {
                return totalDuration;
        }

        public void setTotalDuration(Time totalDuration) {
                this.totalDuration = totalDuration;
        }
}
