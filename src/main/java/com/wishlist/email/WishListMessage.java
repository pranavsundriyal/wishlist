package com.wishlist.email;

import com.wishlist.model.Leg;
import com.wishlist.model.rule.Criteria;
import com.wishlist.model.rule.Rule;
import com.wishlist.model.slim.SearchResult;
import com.wishlist.model.slim.SlimResponse;

/**
 * Created by psundriyal on 3/23/16.
 */
public class WishListMessage {

    public String createMessage(Rule rule, SlimResponse response){

        StringBuffer sb = new StringBuffer();

        sb.append("\nFor Trip\n");

        sb.append(createSubject(rule));
        sb.append("\n\n");

        for (Criteria criteria : rule.getCriterias()){
            sb.append(criteria.getCriteriaType()).append("-").append(criteria.getCriteriaMap().toString()).append("\n");
        }

        sb.append("\nPrices found for the above Criteria\n");

        for (SearchResult searchResult : response.getSearchResultList()){
            sb.append(searchResult.getPrice()).append("\n");
        }


        return sb.toString();
    }

    public String createSubject(Rule rule){
        StringBuffer sb = new StringBuffer();
        sb.append(rule.getOrigin()).append("-").append(rule.getDestination()).append("/")
                .append(rule.getDeparturteDate()).append("/").append(rule.getArrivalDate()).append("\n");

        return sb.toString();
    }

}
