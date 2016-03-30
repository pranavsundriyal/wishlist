package com.wishlist.email;

import com.wishlist.model.Leg;
import com.wishlist.model.Segment;
import com.wishlist.model.rule.Filter;
import com.wishlist.model.rule.Rule;
import com.wishlist.model.slim.SearchResult;
import com.wishlist.model.slim.SlimResponse;
import com.wishlist.util.Util;
import org.springframework.stereotype.Component;

/**
 * Created by psundriyal on 3/23/16.
 * format for url 
 * https://www.expedia.com/Flights-Search?trip=roundtrip&leg1=from:CHI,to:BOM,departure:08/26/2016TANYT&leg2=from:BOM,to:CHI,departure:09/11/2016TANYT&passengers=adults:2&mode=search
 */

@Component
public class WishListMessage {

    public String createMessage(Rule rule, SlimResponse response){

        StringBuffer sb = new StringBuffer();

        sb.append("\nFor Trip\n");

        sb.append(Util.createSubject(rule));
        sb.append("\n\n");

        sb.append("Filters : No. of Search Results\n");
        for (Filter filter : rule.getFilters()){
            sb.append(filter.getFilterType()).append("-").append(filter.getFilterMap().toString()).append(" : ")
                    .append(response.getFilterCountMap().get(filter.getFilterType())).append("\n");
        }

        sb.append("\nTop results found for the above Filters\n");

        sb.append("\n No. of results found: " +response.getSearchResultList().size());
        int noResults=10;
        if (response.getSearchResultList().size() < 10) {
            noResults = response.getSearchResultList().size();
        }
        for (int i =0; i< noResults; i++){
            sb.append("\n Price: " + response.getSearchResultList().get(i).getPrice()+" USD").append("\n");
            for (Leg leg : response.getSearchResultList().get(i).getLegList()) {
                sb.append("Time departure : " +leg.getDepartureTime().toString()+" | arrival: "+leg.getArrivalTime()+"\n");
                sb.append("Total duration: "+Util.addLocalTime(leg.getDuration(),leg.getLayover()).toString()+
                        " | Layover: " +leg.getLayover().toString()+"\n");
                for (Segment segment : leg.getSegments()) {
                    sb.append("Connection Origin: "+segment.getDepartureAirportLocation()+
                            " | Destination: "+segment.getArrivalAirportLocation()+
                            " | Airline: "+segment.getAirlineName());
                    sb.append("\n");
                }
                sb.append("\n");
            }
        }


        return sb.toString();
    }
}
