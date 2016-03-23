package com.wishlist.fliter;

import com.wishlist.model.Leg;
import com.wishlist.model.slim.SearchResult;
import com.wishlist.model.slim.SlimResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by psundriyal on 3/23/16.
 */
public class ArrivalTimeFilter implements Filter {

    private static String HOURS="hours";
    private static String TIME="time";
    private Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    public SlimResponse filter(SlimResponse slimResponse, Map<String, String> criteriaMap) {
        List<SearchResult> filteredSearchResult=new ArrayList<>();
        if (criteriaMap.get(HOURS) !=null && criteriaMap.get(TIME)!=null) {
            for (SearchResult searchResult : slimResponse.getSearchResultList()) {
                if (criteriaMap.get(TIME).equals("after")) {
                    if (searchResult.getLegList().get(searchResult.getLegList().size()-1).getArrivalTime().getHour() >=
                            Integer.parseInt(criteriaMap.get(HOURS))) {
                        filteredSearchResult.add(searchResult);
                    }
                }
                if (criteriaMap.get(TIME).equals("before")) {
                    if (searchResult.getLegList().get(searchResult.getLegList().size()-1).getArrivalTime().getHour() <=
                            Integer.parseInt(criteriaMap.get(HOURS))) {
                        filteredSearchResult.add(searchResult);
                    }
                }
            }
        }

        log.info("In Arrival Time filter applied - search result size: "+filteredSearchResult.size());

        slimResponse.setSearchResultList(filteredSearchResult);
        return slimResponse;
    }
}
