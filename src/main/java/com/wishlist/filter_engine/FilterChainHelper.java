package com.wishlist.filter_engine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wishlist.model.rule.Rule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by psundriyal on 3/30/16.
 */

@Component
public class FilterChainHelper {

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
