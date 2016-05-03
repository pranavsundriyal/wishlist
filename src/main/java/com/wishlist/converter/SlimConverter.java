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


@Component
public class SlimConverter {

    private static String URL = "https://www.expedia.com/Flights-Search?";

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public static SlimResponse createSlimResponse(Response response) {

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
            searchResult.setUrl(computeUrl(searchResult));
            searchResults.add(searchResult);
        }
        Collections.sort(searchResults, new PriceSort());
        slimResponse.setSearchResultList(searchResults);

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
            leg.setTotalDuration(Util.addLocalTimes(leg.getDuration(),leg.getLayover()));
            legMap.put(leg.getLegId(), leg);
        }
        return legMap;
    }

    public static String computeUrl(SearchResult searchResult){

        StringBuffer sb = new StringBuffer(URL);

        if (searchResult.getLegList().size() == 2){
            sb.append("trip=roundtrip");
        } else if (searchResult.getLegList().size() == 1){
            sb.append("trip=oneway");
        } else {
            sb.append("trip=muticity");
        }

        sb.append("&leg1=from:" + searchResult.getLegList().get(0).getSegments().get(0).getDepartureAirportCode()
                +",to:" + searchResult.getLegList().get(0).getSegments().get(searchResult.getLegList().get(0).getSegments().size()-1).getArrivalAirportCode()
                +",departure:"+getDate(searchResult.getLegList().get(0).getDepartureTime())+"TANYT");

        if (searchResult.getLegList().size() ==2){
            sb.append("&leg2=from:"+searchResult.getLegList().get(1).getSegments().get(0).getDepartureAirportCode()
                    +",to:" + searchResult.getLegList().get(1).getSegments().get(searchResult.getLegList().get(1).getSegments().size()-1).getArrivalAirportCode()
                    +",departure:"+getDate(searchResult.getLegList().get(1).getDepartureTime())+"TANYT");
        }

        sb.append("&passengers=adults:1&mode=search");
        return sb.toString();

    }


    public static String getDate(LocalDateTime localDateTime) {
        StringBuffer sb = new StringBuffer();
        sb.append(localDateTime.getMonthValue() + "/" + localDateTime.getDayOfMonth() +"/"+ localDateTime.getYear());

        return sb.toString();
    }
}
