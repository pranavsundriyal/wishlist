package com.wishlist.filter_engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wishlist.compression.CompressionUtil;
import com.wishlist.model.rule.Filter;
import com.wishlist.model.rule.Rule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.GZIPOutputStream;

/**
 * Created by psundriyal on 3/30/16.
 */

@Component
public class FileManager {

    @Value("${setting.filepath}")
    protected String filePath;

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
                append(file,rule);
            } else {
                file.createNewFile();
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean saveGzippedRule(Rule rule) throws IOException{
        BufferedWriter writer = null;
        try{
            File file =new File(filePath+"/"+rule.getEmail());
            file.createNewFile();
            GZIPOutputStream zip = new GZIPOutputStream(new FileOutputStream(file));
            writer = new BufferedWriter(new OutputStreamWriter(zip, "UTF-8"));
            writer.append(CompressionUtil.compressToJSON(rule));
        } catch (FileNotFoundException fe){
            fe.printStackTrace();
        } catch (UnsupportedEncodingException use) {
            use.printStackTrace();
        } catch (IOException io){
            io.printStackTrace();
        }

        finally {
            if (writer != null)
                writer.close();
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

    public  Boolean delete(String email){
        File[] files = new File(filePath).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                if (file.getName().contains(email)) {
                    return deleteFile(file);
                }
            }
        }
        return false;
    }

    public  List<Rule> deleteRule(String email, int ruleNo){
        File[] files = new File(filePath).listFiles();
        List<Rule> ruleList = null;

        for (File file : files) {
            if (file.isFile()) {
                if (file.getName().contains(email)) {
                    String rules = readFile(file);
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        ruleList = mapper.readValue(rules, new TypeReference<List<Rule>>() {});
                        if (ruleNo < ruleList.size()) {
                            ruleList.remove(ruleNo);
                            delete(email);
                            if (ruleList.size() > 0)
                                saveRules(ruleList);
                        }
                    } catch (ArrayIndexOutOfBoundsException a){
                        a.printStackTrace();
                    } catch (NullPointerException npe) {
                        npe.printStackTrace();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
        return ruleList;
    }

    public Boolean saveRules(List<Rule> rules){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String content = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rules);
            File file = new File(filePath+"/"+rules.get(0).getEmail()+".json");
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

    public void append(File file, Rule rule){
        String rulesString =readFile(file);
        deleteFile(file);
        List<Rule> fileRules = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            fileRules = mapper.readValue(rulesString, new TypeReference<List<Rule>>() {
            });
        }catch (IOException e){
            e.printStackTrace();
        }
        fileRules.add(rule);
        try {
            String content = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fileRules);
            File newFile = new File(filePath + "/" + rule.getEmail() + ".json");
            newFile.createNewFile();
            FileWriter fw = new FileWriter(newFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (JsonProcessingException jpe){
            jpe.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

