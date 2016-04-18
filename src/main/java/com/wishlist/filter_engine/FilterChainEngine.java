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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


@Component
public class FilterChainEngine {

    public SlimResponse process(SlimResponse response, List<Rule> ruleList){

        for (Rule rule : ruleList){
            response = processCritera(response, rule.getFilters());
        }
        return response;
    }


    public SlimResponse processCritera(SlimResponse response, List<Filter> filterList){

        for (Filter filter : filterList) {
            if (response.getSearchResultList().size() < 1) {
                return response;
            }
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

            response.getFilterCountMap().put(filter.getFilterType(), response.getSearchResultList().size());
        }
        return response;
    }

}
