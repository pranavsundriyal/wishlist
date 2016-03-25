package com.wishlist.converter;

import com.wishlist.model.Response;
import com.wishlist.model.slim.SlimResponse;
import com.wishlist.model.Leg;
import com.wishlist.model.Offer;
import com.wishlist.model.slim.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class SlimConverter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public SlimResponse createSlimResponse(Response response, Map<String, Leg> legMap) {

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
}
