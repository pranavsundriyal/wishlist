package com.wishlist.filter;

import com.wishlist.model.Leg;
import com.wishlist.model.Segment;
import com.wishlist.model.slim.SearchResult;
import com.wishlist.model.slim.SlimResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by psundriyal on 3/23/16.
 */
public class ConnectionStopsFilter implements Filter {
    private static String LIST = "list";
    private static String QUALIFIER = "qualifier";
    private Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    public SlimResponse filter(SlimResponse slimResponse, Map<String, String> criteriaMap) {
        List<SearchResult> filteredSearchResult = new ArrayList<>();
        if (criteriaMap.get(LIST) != null && criteriaMap.get(QUALIFIER) !=null) {
            List<String> locationList = Arrays.asList(criteriaMap.get(LIST).split("\\s*,\\s*"));
            for (SearchResult searchResult : slimResponse.getSearchResultList()) {
                if (criteriaMap.get(QUALIFIER).equalsIgnoreCase("include")){
                    if (containsLocation(searchResult,locationList)){
                        filteredSearchResult.add(searchResult);
                    }
                }
                if (criteriaMap.get(QUALIFIER).equalsIgnoreCase("exclude")){
                    if (!containsLocation(searchResult,locationList)){
                        filteredSearchResult.add(searchResult);
                    }
                }
            }
        }
        log.info("connection stop filter applied - search result size: " + filteredSearchResult.size());

        slimResponse.setSearchResultList(filteredSearchResult);
        return slimResponse;
    }

    private Boolean containsLocation(SearchResult searchResult, List locationList){

        for (Leg leg : searchResult.getLegList()){
            for (Segment segment : leg.getSegments()){
                if (locationList.contains(segment.getArrivalAirportCode())){
                    return true;
                }
            }
        }
        return false;
    }
}
