package com.wishlist.fliter;

import com.wishlist.model.slim.SlimResponse;
import com.wishlist.model.slim.SearchResult;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class PriceFilter implements Filter {


    private static final String LOWER_PRICE_LIMIT="lowerLimit";

    private Logger log = Logger.getLogger(this.getClass().getName());


    public SlimResponse filter(SlimResponse response, Map<String, String> criteriaMap) {

        List<SearchResult> filteredSearchResults = null;
        if (criteriaMap.get(LOWER_PRICE_LIMIT) != null) {
            filteredSearchResults = response.getSearchResultList().stream().filter(searchResult ->
                    Float.parseFloat(searchResult.getPrice()) < Float.parseFloat(criteriaMap.get(LOWER_PRICE_LIMIT)))
                    .collect(Collectors.toList());
        }

        log.info("price filter applied - search result size: "+filteredSearchResults.size());
        response.setSearchResultList(filteredSearchResults);

        return response;
    }
}
