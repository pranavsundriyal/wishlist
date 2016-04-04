package com.wishlist.service;


import com.wishlist.model.Response;
import com.wishlist.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

@Service("expedia")
public class ExpediaSearchServiceImpl implements Callable {


    private static String API = "https://www.expedia.com/api/flight/search";
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private Request request;
    private Response repsonse;

    /*
    * Need this constructor for caching*/
    public ExpediaSearchServiceImpl() {
    }

    public ExpediaSearchServiceImpl(Request request) {
        this.request = request;
    }


    @Cacheable(value = "results", cacheManager = "springCM")
    public Response execute(Request request) {
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
