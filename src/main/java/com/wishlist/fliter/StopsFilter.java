package com.wishlist.fliter;

import com.wishlist.model.Leg;
import com.wishlist.model.slim.SearchResult;
import com.wishlist.model.slim.SlimResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class StopsFilter implements Filter {



    private Logger log = Logger.getLogger(this.getClass().getName());

    private static String STOPS = "noStops";
    @Override
    public SlimResponse filter(SlimResponse slimResponse, Map<String, String> criteriaMap) {
        List<SearchResult> filteredSearchResults = new ArrayList<>();
        if (criteriaMap.get(STOPS) != null) {
            for (SearchResult searchResult : slimResponse.getSearchResultList()){
                Boolean flag = true;
                for (Leg leg: searchResult.getLegList()){
                    if (leg.getSegments().size()-1> Float.parseFloat(criteriaMap.get(STOPS)))  {
                        flag=false;
                    }
                }
                if (flag) {
                    filteredSearchResults.add(searchResult);
                }
            }
        }

        log.info("stops filter applied - search result size: "+filteredSearchResults.size());

        slimResponse.setSearchResultList(filteredSearchResults);
        return slimResponse;
    }
}
