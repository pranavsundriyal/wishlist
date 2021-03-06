package com.wishlist.util;

import com.wishlist.model.Leg;
import com.wishlist.model.Request;
import com.wishlist.model.Response;
import com.wishlist.model.Segment;
import com.wishlist.model.rule.Rule;
import com.wishlist.model.slim.Time;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class Util {


    private static Logger log = Logger.getLogger(Util.class.getName());

    public static Leg calculateLegDuration(Leg leg) {


        Time layover = new Time(0,0);
        Time totalDuration = new Time(0, 0);
        for (int i =0; i < leg.getSegments().size()-1; i++) {
            Time segTime = parseDurationToTime(leg.getSegments().get(i).getDuration());
            totalDuration = addLocalTimes(totalDuration,segTime);
            LocalDateTime segArrival = parseLocalDateTime(leg.getSegments().get(i).getArrivalTimeRaw());
            LocalDateTime segDeparture = parseLocalDateTime(leg.getSegments().get(i+1).getDepartureTimeRaw());
            Time segLayover = calculateLayoverToTime(segArrival,segDeparture);
            layover = addLocalTimes(segLayover,layover);
        }
        leg.setLayover(layover);
        Time segTime = parseDurationToTime(leg.getSegments().get(leg.getSegments().size()-1).getDuration());
        totalDuration = addLocalTimes(segTime,totalDuration);
        leg.setDuration(totalDuration);
        return leg;
    }

    public static LocalTime parseDuration(String time){

        LocalTime duration = null;
        String[] t = time.split("PT|H|M");
        try {
            if (t[t.length - 2].equals("")) {
                if (Character.toString(time.charAt(time.length() - 1)).equalsIgnoreCase("M")) {
                    duration = LocalTime.of(0, Integer.parseInt(t[t.length - 1]), 0);
                } else if (Character.toString(time.charAt(time.length() - 1)).equalsIgnoreCase("H")) {
                    duration = LocalTime.of(Integer.parseInt(t[t.length - 1]), 0, 0);
                }
            } else {
                duration = LocalTime.of(Integer.parseInt(t[t.length - 2]), Integer.parseInt(t[t.length - 1]), 0);
            }
        } catch (Exception e){
            log.info("message "+e.getMessage());
        }

        return duration;
    }


    public static Time parseDurationToTime(String time){

        Time duration = new Time(0,0);
        String[] t = time.split("PT|H|M");
        try {
            if (t[t.length - 2].equals("")) {
                if (Character.toString(time.charAt(time.length() - 1)).equalsIgnoreCase("M")) {
                    duration.setMinute(Integer.parseInt(t[t.length - 1]));
                } else if (Character.toString(time.charAt(time.length() - 1)).equalsIgnoreCase("H")) {
                    duration.setHour(Integer.parseInt(t[t.length - 1]));
                }
            } else {
                duration = new Time(Integer.parseInt(t[t.length - 2]), Integer.parseInt(t[t.length - 1]));
            }
        } catch (Exception e){
            log.info("message "+e.getMessage());
        }

        return duration;
    }

    public static LocalTime calculateLayover(LocalDateTime segArrival, LocalDateTime segDeparture) {

        long  minutes = ChronoUnit.MINUTES.between(segArrival, segDeparture);
        int hours = (int) ChronoUnit.HOURS.between(segArrival, segDeparture);
        int actualMinutes = (int)(minutes-hours*60);
        return LocalTime.of(hours, actualMinutes);
    }

    public static Time calculateLayoverToTime(LocalDateTime segArrival, LocalDateTime segDeparture) {

        long  minutes = ChronoUnit.MINUTES.between(segArrival, segDeparture);
        int hours = (int) ChronoUnit.HOURS.between(segArrival, segDeparture);
        int actualMinutes = (int)(minutes-hours*60);
        return new Time(hours, actualMinutes);
    }


    public static LocalDateTime parseLocalDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        return localDateTime;
    }

    public static LocalTime addLocalTime(LocalTime t1, LocalTime t2){

        int m = t1.getMinute() + t2.getMinute();
        int h = m/60;
        int min = m%60;
        int hour = t1.getHour() + t2.getHour() + h;
        return LocalTime.of(hour,min,0,0);
    }

    public static Time addLocalTimes(LocalTime t1, LocalTime t2){

        int m = t1.getMinute() + t2.getMinute();
        int h = m/60;
        int min = m%60;
        int hour = t1.getHour() + t2.getHour() + h;
        return new Time(hour,min);
    }

    public static Time addLocalTimes(Time t1, Time t2){

        int m = t1.getMinute() + t2.getMinute();
        int h = m/60;
        int min = m%60;
        int hour = t1.getHour() + t2.getHour() + h;
        return new Time(hour,min);
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


    public static float getMinutes(int hours, int minutes) {
        return hours*60+minutes;
    }

    public static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap) {

        // Convert Map to List
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(unsortMap.entrySet());

        // Sort list with comparator, to compare the Map values
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // Convert sorted map back to a Map
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

}
