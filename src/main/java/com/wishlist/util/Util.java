package com.wishlist.util;

import com.wishlist.model.Leg;
import com.wishlist.model.Request;
import com.wishlist.model.Response;
import com.wishlist.model.Segment;
import com.wishlist.model.rule.Rule;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by psundriyal on 3/22/16.
 */
public class Util {


    private static Logger log = Logger.getLogger(Util.class.getName());


    public static Map<String, Leg> mapLegs(Response response){

        Map<String, Leg> legMap = new HashMap<>();


        for (Leg leg :response.getLegs()) {
            LocalDateTime departureTime = parseLocalDateTime(leg.getSegments().get(0).getDepartureTimeRaw());
            LocalDateTime arrivalTime = parseLocalDateTime(leg.getSegments().get(leg.getSegments().size()-1).
                    getArrivalTimeRaw());
            leg.setArrivalTime(arrivalTime);
            leg.setDepartureTime(departureTime);
            calculateLegDuration(leg);
            legMap.put(leg.getLegId(), leg);
        }

        return legMap;
    }



    public static Leg calculateLegDuration(Leg leg) {


        LocalTime layover = LocalTime.of(0,0,0);
        LocalTime totalDuration = LocalTime.of(0, 0, 0);
        for (int i =0; i < leg.getSegments().size()-1; i++) {
            LocalTime segTime = parseDuration(leg.getSegments().get(i).getDuration());
            totalDuration = addLocalTime(totalDuration,segTime);
            LocalDateTime segArrival = parseLocalDateTime(leg.getSegments().get(i).getArrivalTimeRaw());
            LocalDateTime segDeparture = parseLocalDateTime(leg.getSegments().get(i+1).getDepartureTimeRaw());
            LocalTime segLayover = calculateLayover(segArrival,segDeparture);
            layover = addLocalTime(segLayover,layover);
        }
        leg.setLayover(layover);
        LocalTime segTime = parseDuration(leg.getSegments().get(leg.getSegments().size()-1).getDuration());
        totalDuration = addLocalTime(segTime,totalDuration);
        leg.setDuration(totalDuration);
        return leg;
    }

    public static LocalTime parseDuration(String time){

        LocalTime duration = null;
        String[] t = time.split("PT|H|M");

        if (t[t.length-2].equals("")){
            if (Character.toString(time.charAt(time.length()-1)).equalsIgnoreCase("M")) {
                duration = LocalTime.of(0, Integer.parseInt(t[t.length - 1]), 0);
            } else if (Character.toString(time.charAt(time.length()-1)).equalsIgnoreCase("H")) {
                duration = LocalTime.of(Integer.parseInt(t[t.length - 1]), 0, 0);
            }
        } else {
            duration = LocalTime.of(Integer.parseInt(t[t.length - 2]), Integer.parseInt(t[t.length - 1]), 0);
        }

        return duration;
    }

    public static LocalTime calculateLayover(LocalDateTime segArrival, LocalDateTime segDeparture) {

        long  minutes = ChronoUnit.MINUTES.between(segArrival, segDeparture);
        int hours = (int) ChronoUnit.HOURS.between(segArrival, segDeparture);
        int actualMinutes = (int)(minutes-hours*60);
        return LocalTime.of(hours, actualMinutes);
    }


    public static LocalDateTime parseLocalDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        return localDateTime;
    }

    public static LocalTime addLocalTime(LocalTime t1, LocalTime t2){

        t2 = t2.plusMinutes(new Long(t1.getMinute()));
        t2 = t2.plusHours(new Long(t1.getHour()));
        return t2;
    }

    public static Request creatRequestFromRule(Rule rule){
        Request request;
        if (rule.getArrivalDate() == null) {
            request = new Request(rule.getDeparturteDate(), rule.getOrigin(), rule.getDestination());
        } else {
            request = new Request(rule.getArrivalDate(), rule.getDeparturteDate(), rule.getOrigin(),
                    rule.getDestination());
        }
        return request;
    }

    public static String addDate(String dateString, int noDays) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException pe){
            pe.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, noDays);

        return formatter.format(c.getTime());
    }


}
