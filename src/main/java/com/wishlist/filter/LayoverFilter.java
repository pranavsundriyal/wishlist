package com.wishlist.filter;

import com.wishlist.model.Leg;
import com.wishlist.model.slim.SearchResult;
import com.wishlist.model.slim.SlimResponse;
import com.wishlist.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class LayoverFilter implements Filter {

    private static String HOURS="hours";
    private Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    public SlimResponse filter(SlimResponse slimResponse, Map<String, String> criteriaMap) {
        List<SearchResult> filteredSearchResult=new ArrayList<>();
        if (criteriaMap.get(HOURS) !=null) {
            for (SearchResult searchResult : slimResponse.getSearchResultList()) {
                boolean flag = true;
                for (Leg leg : searchResult.getLegList()) {
                    if (Util.getMinutes(leg.getLayover().getHour(),leg.getLayover().getMinute()) >
                            Integer.parseInt(criteriaMap.get(HOURS))*60){
                        flag=false;
                    }
                }
                if (flag){
                    filteredSearchResult.add(searchResult);
                }
            }
        }

        log.info("layover filter applied - search result size: "+filteredSearchResult.size());

        slimResponse.setSearchResultList(filteredSearchResult);
        return slimResponse;
    }
}
