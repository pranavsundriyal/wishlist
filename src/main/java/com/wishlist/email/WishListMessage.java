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
 */

@Component
public class WishListMessage {

    private static String URL = "https://www.expedia.com/Flights-Search?";

    public static String createMessage(Rule rule, SlimResponse response){

        StringBuffer sb = new StringBuffer();

        sb.append("\nFor Trip\n");

        sb.append(createSubject(rule));
        sb.append("\n\n");

        sb.append("Filters : No. of Search Results\n");
        for (Filter filter : rule.getFilters()){
            sb.append(filter.getFilterType()).append("-").append(filter.getFilterMap().toString()).append(" : ")
                    .append(response.getFilterCountMap().get(filter.getFilterType())).append("\n");
        }

        sb.append("\nTop results found for the above Filters\n");
        sb.append("url : "+createURL(rule)+"\n");
        sb.append("\n No. of results found: " +response.getSearchResultList().size() + " showing top 10 or less");
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

    public static String createSubject(Rule rule){
        StringBuffer sb = new StringBuffer();
        sb.append(rule.getOrigin()).append("-").append(rule.getDestination()).append("/")
                .append(rule.getDeparturteDate()).append("/").append(rule.getArrivalDate());
        if (rule.getFlex()){
            sb.append("/FLEX");
        }
        sb.append("\n");
        return sb.toString();
    }

    public static String createURL(Rule rule){

        StringBuffer sb = new StringBuffer(URL);
        if (rule.getArrivalDate() != null){
            sb.append("trip=roundtrip");
        } else {
            sb.append("trip=oneway");
        }

        sb.append("&leg1=from:"+rule.getOrigin()
                +",to:"+rule.getDestination()
                +",departure:"+formatDate(rule.getDeparturteDate())+"TANYT");

        if (rule.getArrivalDate() != null){
            sb.append("&leg2=from:"+rule.getDestination()
                    +",to:"+rule.getOrigin()
                    +",departure:"+formatDate(rule.getArrivalDate())+"TANYT");
        }

        sb.append("&passengers=adults:1&mode=search");
        return sb.toString();
    }

    public static String formatDate(String date) {
        String s= date.replace('-','/');
        return s.substring(5)+"/"+s.substring(0,4);
    }
}
