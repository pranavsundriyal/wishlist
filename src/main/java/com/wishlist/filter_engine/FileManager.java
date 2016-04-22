package com.wishlist.filter_engine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wishlist.model.rule.Rule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by psundriyal on 3/30/16.
 */

@Component
public class FileManager {

    @Value("${setting.filepath}")
    private String filePath;

    public List<Rule> readRules() {

        List<String> files = getFiles();
        List<Rule> ruleList = new ArrayList();
        ObjectMapper mapper = new ObjectMapper();
        try {
            for (String rulesString: files) {
                List<Rule> fileRules = mapper.readValue(rulesString, new TypeReference<List<Rule>>() {});
                ruleList.addAll(fileRules);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return ruleList;
    }


    public List<String> getFiles() {
        List<String> results = new ArrayList<>();
        File[] files = new File(filePath).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                results.add(readFile(file));
            }
        }
        return results;
    }

    public String readFile(File file){
        StringBuilder result = new StringBuilder("");

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

    public Boolean saveRule(Rule rule){
        try {
            List<Rule> rules = new ArrayList<>();
            rules.add(rule);
            ObjectMapper objectMapper = new ObjectMapper();
            String content = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rules);
            File file = new File(filePath+"/"+rule.getEmail()+".json");
            if (file.exists()) {
                deleteFile(file);
            }
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean deleteFile(File file){

        try{
            if(file.delete()){
                return true;
            }else{
                return false;
            }

        }catch(Exception e){

            e.printStackTrace();

        }
        return false;
    }
    public List<String> lookUp(String email) {
        List<String> results = new ArrayList<>();
        File[] files = new File(filePath).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                if (file.getName().contains(email)) {
                    results.add(readFile(file));
                    return results;
                }
            }
        }
        return null;
    }
}

