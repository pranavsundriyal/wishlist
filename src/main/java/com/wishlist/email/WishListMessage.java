package com.wishlist.email;

import com.wishlist.model.Leg;
import com.wishlist.model.rule.Filter;
import com.wishlist.model.rule.Rule;
import com.wishlist.model.slim.SearchResult;
import com.wishlist.model.slim.SlimResponse;
import com.wishlist.util.Util;

/**
 * Created by psundriyal on 3/23/16.
 */
public class WishListMessage {

    public String createMessage(Rule rule, SlimResponse response){

        StringBuffer sb = new StringBuffer();

        sb.append("\nFor Trip\n");

        sb.append(Util.createSubject(rule));
        sb.append("\n\n");

        sb.append("Filters\n");
        for (Filter filter : rule.getFilters()){
            sb.append(filter.getFilterType()).append("-").append(filter.getFilterMap().toString()).append("\n");
        }

        sb.append("\nPrices found for the above Filters\n");

        for (SearchResult searchResult : response.getSearchResultList()){
            sb.append(searchResult.getPrice()).append("\n");
        }


        return sb.toString();
    }
}
