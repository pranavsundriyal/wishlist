package com.wishlist.email;

import com.wishlist.model.Leg;
import com.wishlist.model.Segment;
import com.wishlist.model.rule.Filter;
import com.wishlist.model.rule.Rule;
import com.wishlist.model.slim.SlimResponse;
import com.wishlist.model.slim.Time;
import com.wishlist.util.Util;

import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by psundriyal on 3/23/16.
 */

@Component
public class WishListMessage {

    private static String URL = "https://www.expedia.com/Flights-Search?";

    private static Logger log = Logger.getLogger(WishListMessage.class.getName());

    public static String createMessage(Rule rule, SlimResponse response){

        StringBuffer sb = new StringBuffer();

        sb.append("<html>");
        sb.append("<head>\n")
                .append("   <style>")
                .append("   table {border-collapse:collapse; table-layout:fixed; width:310px;}")
                .append("   table td {border:solid 1px; width:225px; word-wrap:break-word; padding:3px;}")
                .append("   div.asdf {width:693px; white-space: pre-wrap; white-space: -moz-pre-wrap; white-space: -pre-wrap; white-space: -o-pre-wrap; word-wrap: break-word;}")
                .append("   </style>")
                .append("</head>");
//        sb.append("<html>" +
//                "<table>" +
//                "<tr>" +
//                "<td>r1c1</td>" +
//                "<td>r1c2</td>" +
//                "</tr>" +
//                "<tr>" +
//                "<td>r2c1</td>" +
//                "<td>r2c2</td>" +
//                "</tr>" +
//                "</table>");

        sb.append("<body>");

        sb.append("<br>For Trip<br>");

        sb.append(createSubject(rule));
        sb.append("<br><br>");

        sb.append("<table border=\"1\"><tr><td>Filter</td><td>Value</td><td>No. of search results</td></tr>");

//        sb.append("Filters : No. of Search Results\n");
        for (Filter filter : rule.getFilters()){
            sb.append("<tr>")
                    .append("<td>")
                    .append(filter.getFilterType())
                    .append("</td>")
//                    .append("-")
                    .append("<td>")
                    .append(filter.getFilterMap().toString())
                    .append("</td>")
//                    .append(" : ")
                    .append("<td>")
                    .append(response.getFilterCountMap().get(filter.getFilterType()))
                    .append("</td>")
                    .append("</tr>");
//                    .append("\n");
        }

        sb.append("</table>");

        sb.append("<br>Top results found for the above Filters<br>");
        sb.append("<div class='asdf'>url : "+createURL(rule)+"<br></div>");
        sb.append("No. of results found: " +response.getSearchResultList().size() + " <br>Showing top 10 or less results<br><br>");
        int noResults=10;
        if (response.getSearchResultList().size() < 10) {
            noResults = response.getSearchResultList().size();
        }

        sb.append("<table border=\"1\"><tr><td>Price</td><td>Schedule</td><td>Link</td></tr>");

        for (int i =0; i< noResults; i++){
            sb.append("<tr>");
            sb.append("<td>" + response.getSearchResultList().get(i).getPrice() + " USD</td>");
            sb.append("<td>");
            int n = 1;
            for (Leg leg : response.getSearchResultList().get(i).getLegList()) {
                sb.append("Slice #" + n + ":<br>");
                sb.append("Departure time: " +leg.getDepartureTime().toString()+"<br>Arrival time: "+leg.getArrivalTime()+"<br>");
                Time totalDuration = Util.addLocalTimes(leg.getDuration(),leg.getLayover());
                Time layover = leg.getLayover();
                sb.append("Total duration: "+totalDuration.getHour()+" hour " + totalDuration.getMinute() + " minute<br>Layover: " +layover.getHour() +" hour "+layover.getMinute()+" minute<br>");
                sb.append("Segments:<br>");
                for (Segment segment : leg.getSegments()) {
                    sb.append("Origin: "+segment.getDepartureAirportLocation()+
                            "<br>Destination: "+segment.getArrivalAirportLocation()+
                            "<br>Airline: "+segment.getAirlineName());
                    sb.append("<br>");
                }
                sb.append("<br>");
                n++;
            }
            sb.append("</td>");
            sb.append("<td width=\"100\">"+response.getSearchResultList().get(i).getUrl()+"</td>");
            sb.append("</tr>");
        }

        sb.append("</table>");

        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    public static String createSubject(Rule rule){
        StringBuffer sb = new StringBuffer();
        sb.append(rule.getOrigin()).append("-").append(rule.getDestination()).append("/")
                .append(rule.getDeparturteDate()).append("/").append(rule.getArrivalDate());

        if (Integer.parseInt(rule.getFlex()) > 0){
            sb.append("/FLEX+"+rule.getFlex());
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
        String s = date.replace('-','/');
        String d = null;
        try {
            d = s.substring(5) + "/" + s.substring(0, 4);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return d;
    }
}
