package com.wishlist.rule_engine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wishlist.fliter.AirlineFilter;
import com.wishlist.fliter.ArrivalTimeFilter;
import com.wishlist.fliter.DepartureTimeFilter;
import com.wishlist.fliter.DurationFilter;
import com.wishlist.fliter.InFlightDuration;
import com.wishlist.fliter.LayoverFilter;
import com.wishlist.fliter.PriceFilter;
import com.wishlist.fliter.StopsFilter;
import com.wishlist.model.rule.Criteria;
import com.wishlist.model.rule.Rule;
import com.wishlist.model.slim.SlimResponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


@Component
public class RuleEngine {


    public SlimResponse process(SlimResponse response, List<Rule> ruleList){

        for (Rule rule : ruleList){
            response=processCritera(response, rule.getCriterias());
        }
        return response;
    }


    public SlimResponse processCritera(SlimResponse response, List<Criteria> criteriaList){

        for (Criteria criteria : criteriaList) {
            if (criteria.getCriteriaType().equalsIgnoreCase("price")) {
                response = new PriceFilter().filter(response, criteria.getCriteriaMap());
            } else if (criteria.getCriteriaType().equalsIgnoreCase("layover")) {
                response = new LayoverFilter().filter(response, criteria.getCriteriaMap());
            } else if (criteria.getCriteriaType().equalsIgnoreCase("stops")) {
                response = new StopsFilter().filter(response, criteria.getCriteriaMap());
            } else if (criteria.getCriteriaType().equalsIgnoreCase("duration")) {
                response = new DurationFilter().filter(response, criteria.getCriteriaMap());
            } else if (criteria.getCriteriaType().equalsIgnoreCase("flightDuration")) {
                response = new InFlightDuration().filter(response, criteria.getCriteriaMap());
            } else if (criteria.getCriteriaType().equalsIgnoreCase("arrivalTime")) {
                response = new ArrivalTimeFilter().filter(response, criteria.getCriteriaMap());
            } else if (criteria.getCriteriaType().equalsIgnoreCase("departureTime")) {
                response = new DepartureTimeFilter().filter(response, criteria.getCriteriaMap());
            } else if (criteria.getCriteriaType().equalsIgnoreCase("airline")) {
                response = new AirlineFilter().filter(response, criteria.getCriteriaMap());
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
