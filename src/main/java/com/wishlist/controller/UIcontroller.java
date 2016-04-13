package com.wishlist.controller;

import com.wishlist.converter.SlimConverter;
import com.wishlist.filter_engine.FilterChainEngine;
import com.wishlist.filter_engine.FilterChainHelper;
import com.wishlist.model.Request;
import com.wishlist.model.Response;
import com.wishlist.model.slim.SlimResponse;
import com.wishlist.service.ExpediaSearchServiceImpl;
import com.wishlist.thread.ThreadManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * Created by psundriyal on 4/12/16.
 */

@RestController
public class UIcontroller {
    private Logger log = Logger.getLogger(SearchController.class.getName());

    @Autowired
    private ExpediaSearchServiceImpl searchService;

    @Autowired
    private ThreadManager threadManager;

    @Autowired
    private FilterChainEngine filterChainEngine;

    @Autowired
    private FilterChainHelper filterChainHelper;


    @RequestMapping(value = "/searchExp", method = RequestMethod.GET)
    public SlimResponse search(@RequestParam(value="origin", required=true) String origin,
                               @RequestParam(value="dest", required=true) String destination,
                               @RequestParam(value="departure", required=true) String departureDate,
                               @RequestParam(value="arrival", required=false) String arrivalDate) throws Exception {

        Request request;
        if (arrivalDate == null) {
            request = new Request(departureDate, origin, destination);
        } else {
            request = new Request(arrivalDate,departureDate, origin, destination);
        }
        Response response = searchService.execute(request);

        SlimResponse slimResponse = new SlimConverter().createSlimResponse(response);

        log.info("total search results : "+slimResponse.getSearchResultList().size());

        return slimResponse;
    }
}
