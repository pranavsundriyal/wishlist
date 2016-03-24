package com.wishlist.filter_engine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wishlist.filter.AirlineFilter;
import com.wishlist.filter.ArrivalTimeFilter;
import com.wishlist.filter.ConnectionStopsFilter;
import com.wishlist.filter.DepartureTimeFilter;
import com.wishlist.filter.DurationFilter;
import com.wishlist.filter.InFlightDuration;
import com.wishlist.filter.LayoverFilter;
import com.wishlist.filter.PriceFilter;
import com.wishlist.filter.StopsFilter;
import com.wishlist.model.rule.Filter;
import com.wishlist.model.rule.Filter;
import com.wishlist.model.rule.Rule;
import com.wishlist.model.slim.SlimResponse;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


@Component
public class FilterChainEngine {


    public SlimResponse process(SlimResponse response, List<Rule> ruleList){

        for (Rule rule : ruleList){
            response=processCritera(response, rule.getFilters());
        }
        return response;
    }


    public SlimResponse processCritera(SlimResponse response, List<Filter> filterList){

        for (Filter filter : filterList) {
            if (filter.getFilterType().equalsIgnoreCase("price")) {
                response = new PriceFilter().filter(response, filter.getFilterMap());
            } else if (filter.getFilterType().equalsIgnoreCase("layover")) {
                response = new LayoverFilter().filter(response, filter.getFilterMap());
            } else if (filter.getFilterType().equalsIgnoreCase("stops")) {
                response = new StopsFilter().filter(response, filter.getFilterMap());
            } else if (filter.getFilterType().equalsIgnoreCase("duration")) {
                response = new DurationFilter().filter(response, filter.getFilterMap());
            } else if (filter.getFilterType().equalsIgnoreCase("flightDuration")) {
                response = new InFlightDuration().filter(response, filter.getFilterMap());
            } else if (filter.getFilterType().equalsIgnoreCase("arrivalTime")) {
                response = new ArrivalTimeFilter().filter(response, filter.getFilterMap());
            } else if (filter.getFilterType().equalsIgnoreCase("departureTime")) {
                response = new DepartureTimeFilter().filter(response, filter.getFilterMap());
            } else if (filter.getFilterType().equalsIgnoreCase("airline")) {
                response = new AirlineFilter().filter(response, filter.getFilterMap());
            } else if (filter.getFilterType().equalsIgnoreCase("connectionStop")) {
                response = new ConnectionStopsFilter().filter(response, filter.getFilterMap());
            }
        }
        return response;
    }


    public List<Rule> readRules() {

        String rulesString = getFile("rules.json");
        List<Rule> ruleList = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            ruleList = mapper.readValue(rulesString, new TypeReference<List<Rule>>() {
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return ruleList;
    }

    private String getFile(String fileName) {

        StringBuilder result = new StringBuilder("");

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
