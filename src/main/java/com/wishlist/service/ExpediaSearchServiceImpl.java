package com.wishlist.service;


import com.wishlist.model.Response;
import com.wishlist.model.Request;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

@Service
public class ExpediaSearchServiceImpl implements Callable,SearchService {


    private static String API = "https://www.expedia.com/api/flight/search";
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private Request request;
    private Response repsonse;

    @Autowired
    private CacheManager cacheManager;
    /*
    * Need this constructor for caching*/
    public ExpediaSearchServiceImpl() {
    }

    public ExpediaSearchServiceImpl(Request request) {
        this.request = request;
    }

    public Response execute(Request request) {
        Cache cache = cacheManager.getCache("cache");
        Response response = null;
        Element elementResponse = cache.get(request.toString());
        if (elementResponse == null) {

            String url = API + getParams(request);
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.getForObject(url, Response.class);
            Element element = new Element(request.toString(), response);
            cache.put(element);
        } else {
            response = (Response) elementResponse.getObjectValue();
        }
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


    public Response getRepsonse() {
        return repsonse;
    }

    public void setRepsonse(Response repsonse) {
        this.repsonse = repsonse;
    }

    @Override
    public Response call() throws Exception {
        return execute(request);
    }
}
