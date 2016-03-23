package com.wishlist.rule_engine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wishlist.fliter.PriceFilter;
import com.wishlist.model.rule.Criteria;
import com.wishlist.model.rule.Rule;
import com.wishlist.model.slim.SlimResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class RuleEngine {


    public SlimResponse process(SlimResponse response){

        List<Rule> ruleList = readRule();

        for (Rule rule : ruleList){
            for (Criteria criteria : rule.getCriterias()){
                if (criteria.getCriteriaType().equalsIgnoreCase("price")) {
                    response = new PriceFilter().filter(response,criteria.getCriteriaMap());
                }
            }
        }
        return response;
    }



    public List<Rule> readRule() {

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
