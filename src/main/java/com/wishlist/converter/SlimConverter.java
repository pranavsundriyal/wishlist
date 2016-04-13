package com.wishlist.converter;

import com.wishlist.model.Response;
import com.wishlist.model.slim.SlimResponse;
import com.wishlist.model.Leg;
import com.wishlist.model.Offer;
import com.wishlist.model.slim.SearchResult;
import com.wishlist.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SlimConverter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public SlimResponse createSlimResponse(Response response) {

        Map<String, Leg> legMap = mapLegs(response);

        SlimResponse slimResponse = new SlimResponse();

        List<SearchResult> searchResults = new ArrayList<>();
        for (Offer offer : response.getOffers()) {
            SearchResult searchResult = new SearchResult();
            searchResult.setPrice(offer.getTotalFare());
            List<Leg> legList = new ArrayList<>();
            for (String legId : offer.getLegIds()) {
                legList.add(legMap.get(legId));
            }
            searchResult.setLegList(legList);
            searchResults.add(searchResult);
        }
        PriceSort priceSort = new PriceSort();
        Collections.sort(searchResults, priceSort);
        slimResponse.setSearchResultList(searchResults);

        log.info("Total search results : "+slimResponse.getSearchResultList().size());
        return slimResponse;
    }


    public static Map<String, Leg> mapLegs(Response response){

        Map<String, Leg> legMap = new HashMap<>();
        for (Leg leg :response.getLegs()) {
            LocalDateTime departureTime = Util.parseLocalDateTime(leg.getSegments().get(0).getDepartureTimeRaw());
            LocalDateTime arrivalTime = Util.parseLocalDateTime(leg.getSegments().get(leg.getSegments().size()-1).
                    getArrivalTimeRaw());
            leg.setArrivalTime(arrivalTime);
            leg.setDepartureTime(departureTime);
            Util.calculateLegDuration(leg);
            legMap.put(leg.getLegId(), leg);
        }
        return legMap;
    }

}
