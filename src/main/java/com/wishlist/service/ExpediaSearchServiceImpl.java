package com.wishlist.service;


import com.wishlist.model.Response;
import com.wishlist.model.Request;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

@Component
@Scope("prototype")
public class ExpediaSearchServiceImpl implements Callable {


    private static String API = "https://www.expedia.com/api/flight/search";
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private Request request;

    @Autowired
    private CacheManager cacheManager;
    /*
    * Need this constructor for caching*/
    public ExpediaSearchServiceImpl() {
    }

    public ExpediaSearchServiceImpl(Request request, CacheManager cacheManager) {
        this.request = request;
        this.cacheManager = cacheManager;
    }

    public Response execute() {
        Response response = null;

        if (cacheManager != null) {
            Cache cache = cacheManager.getCache("cache");
            Element elementResponse = cache.get(request.toString());
            if (elementResponse != null) {
                response = (Response) elementResponse.getObjectValue();
                return response;
            }
            else {
                response = executeCall();
                Element element = new Element(request.toString(), response);
                cache.put(element);
                return response;
            }
        }
        return executeCall();
    }

    public Response executeCall(){
        String url = API + getParams(request);
        RestTemplate restTemplate = new RestTemplate();
        Response response = restTemplate.getForObject(url, Response.class);
        return response;
    }
    public String getParams(Request request) {
        StringBuilder params = new StringBuilder("?");
        String departureDate = request.getDeparturteDate();
        params.append("departureDate=").append(departureDate).append("&departureAirport=")
                .append(request.getOrigin()).append("&arrivalAirport=")
                .append(request.getDestination());

        if (null != request.getArrivalDate()) {
            params.append("&returnDate=").append(request.getArrivalDate());
        }

        params.append("&maxOfferCount=" + request.getMaxOffer());
        params.append("&eapid=128087");
        return params.toString();
    }


    @Override
    public Response call() throws Exception {
        return execute();
    }
}
