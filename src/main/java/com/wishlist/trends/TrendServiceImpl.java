package com.wishlist.trends;

import com.wishlist.compression.CompressionUtil;
import com.wishlist.model.Request;
import com.wishlist.model.Response;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

/**
 * Created by psundriyal on 5/15/16.
 * http://terminal2.expedia.com/x/flights/v3/search/1/SEA/LAS/2016-06-12?apikey=bSsA2klOU6l5YTbkREUVanlHVS9KXxff
 */


@Component
public class TrendServiceImpl {


    private static String API = "http://terminal2.expedia.com/x/flights/v3/search/1/";
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static String API_KEY = "?apikey=";

    @Value("${setting.trendPass}")
    private String KEY ;


    public String executeCall(Request request){
        String url = API + getParams(request);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }
    public String getParams(Request request) {
        StringBuilder params = new StringBuilder();
        params.append(request.getOrigin()+"/"+request.getDestination()+"/"+request.getDeparturteDate());
        params.append(API_KEY+KEY);
        return params.toString();
    }
}
