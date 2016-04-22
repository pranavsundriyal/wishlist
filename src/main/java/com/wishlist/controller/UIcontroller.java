package com.wishlist.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wishlist.converter.SlimConverter;
import com.wishlist.filter_engine.FilterChainEngine;
import com.wishlist.filter_engine.FileManager;
import com.wishlist.model.Request;
import com.wishlist.model.Response;
import com.wishlist.model.rule.Filter;
import com.wishlist.model.rule.Rule;
import com.wishlist.model.slim.SlimResponse;
import com.wishlist.service.ExpediaSearchServiceImpl;
import com.wishlist.thread.FlexThreadManager;
import com.wishlist.thread.ThreadManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by psundriyal on 4/12/16.
 */

@RestController
@Component
public class UIcontroller {
    private Logger log = Logger.getLogger(SearchController.class.getName());

    @Autowired
    private ThreadManager threadManager;

    @Autowired
    private FilterChainEngine filterChainEngine;

    @Autowired
    private FileManager fileManager;

    @Autowired
    private ExpediaSearchServiceImpl expediaSearchService;

    @Autowired
    private FlexThreadManager flexThreadManager;


    @RequestMapping(value = "/searchExp", method = RequestMethod.GET)
    public SlimResponse search(@RequestParam(value="origin", required=true) String origin,
                               @RequestParam(value="dest", required=true) String destination,
                               @RequestParam(value="departure", required=true) String departureDate,
                               @RequestParam(value="arrival", required=false) String arrivalDate,
                               @RequestParam(value = "filters", required = false) String filters,
                               @RequestParam(value = "flex", required = false) boolean flex) throws Exception {

        Request request;
        if (arrivalDate == null) {
            request = new Request(departureDate, origin, destination);
        } else {
            request = new Request(arrivalDate,departureDate, origin, destination);
        }
        List<Filter> filterList = getFilterList(filters);
        long startTime = System.currentTimeMillis();
        SlimResponse slimResponse;
        if (flex)
            slimResponse = flexThreadManager.getFlexResponses(request);
        else {
            Response response = expediaSearchService.execute(request);
            slimResponse = new SlimConverter().createSlimResponse(response);
        }
        log.info("time taken: " + (System.currentTimeMillis()-startTime));

        log.info("total search results : "+slimResponse.getSearchResultList().size());

        slimResponse = filterChainEngine.processCritera(slimResponse, filterList);

        return slimResponse;

    }


    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public Boolean save(@RequestParam(value="origin", required=true) String origin,
                               @RequestParam(value="dest", required=true) String destination,
                               @RequestParam(value="departure", required=true) String departureDate,
                               @RequestParam(value="arrival", required=false) String arrivalDate,
                               @RequestParam(value = "filters", required = false) String filters,
                               @RequestParam(value = "flex", required = false) boolean flex,
                                @RequestParam(value="email", required=false) String email) throws Exception {

        List<Filter> filterList = getFilterList(filters);
        Rule rule = new Rule();
        rule.setEmail(email);
        rule.setOrigin(origin);
        rule.setDestination(destination);
        rule.setArrivalDate(arrivalDate);
        rule.setDeparturteDate(departureDate);
        rule.setFlex(flex);
        rule.setFilters(filterList);
        return fileManager.saveRule(rule);
    }

    public List<Filter> getFilterList(String filters){
        List<Filter> filterList = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            filterList = mapper.readValue(filters, new TypeReference<List<Filter>>() {
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return filterList;
    }
}
