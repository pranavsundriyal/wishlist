package com.wishlist.trends;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wishlist.compression.CompressionUtil;
import com.wishlist.model.Request;
import com.wishlist.model.Response;
import com.wishlist.model.trends.Trend;
import com.wishlist.model.trends.TrendResponse;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;

/**
 * Created by psundriyal on 5/15/16.
 *
 */


@Component
public class TrendServiceImpl {


    private static String API = "http://terminal2.expedia.com/x/flights/v3/search/1/";
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static String API_KEY = "?apikey=";

    @Value("${setting.trendPass}")
    private String KEY ;


    public TrendResponse executeCall(Request request){
        String url = API + getParams(request);
        RestTemplate restTemplate = new RestTemplate();
        TrendResponse response = restTemplate.getForObject(url, TrendResponse.class);
        //TrendResponse response = readJson();

        return postProcessing(response);
    }
    public String getParams(Request request) {
        StringBuilder params = new StringBuilder();
        params.append(request.getOrigin()+"/"+request.getDestination()+"/"+request.getDeparturteDate());
        params.append(API_KEY+KEY);
        return params.toString();
    }

    public String read(){
        BufferedReader br = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("/Users/psundriyal/expedia/wishlist/src/main/resources/trend/trend.json"));

            while ((sCurrentLine = br.readLine()) != null) {
                stringBuffer.append(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

    public TrendResponse readJson() {
        TrendResponse response = null;
        String json = read();
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            response = objectMapper.readValue(json, TrendResponse.class);
        } catch (IOException e) {
            log.info(e.getMessage());
        }

        return response;
    }

    public TrendResponse postProcessing(TrendResponse trendResponse) {

        List<Trend> trends = new ArrayList<>();
        float lowest = 999999;
        Stack<Trend> trendStack = new Stack<>();
        for (Trend trend : trendResponse.getRecommended().getTrends()) {
            trendStack.push(trend);
            if (Float.parseFloat(trend.getMin()) < lowest){
                lowest = Float.parseFloat(trend.getMin());
            }
        }

        while (!trendStack.empty()){
            trends.add(trendStack.pop());
        }
        trendResponse.getRecommended().setTrends(trends);
        trendResponse.setLowestPrice(Float.toString(lowest));
        trendResponse.setLastSeenPrice(trendResponse.getRecommended().getTrends().get(trendResponse.getRecommended()
                .getTrends().size()-1).getMin());
        return trendResponse;
    }
}
