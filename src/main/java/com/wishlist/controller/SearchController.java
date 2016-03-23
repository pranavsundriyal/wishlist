package com.wishlist.controller;

import com.wishlist.converter.SlimConverter;
import com.wishlist.model.Leg;
import com.wishlist.model.Request;
import com.wishlist.model.Response;
import com.wishlist.model.rule.Rule;
import com.wishlist.model.slim.SlimResponse;
import com.wishlist.rule_engine.RuleEngine;
import com.wishlist.service.ExpediaSearchServiceImpl;
import com.wishlist.thread.ThreadManager;
import com.wishlist.util.Util;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class SearchController {

    private Logger log = Logger.getLogger(SearchController.class.getName());

    @Autowired
    private ExpediaSearchServiceImpl searchService;

    @Autowired
    private ThreadManager threadManager;

    @Autowired
    private RuleEngine ruleEngine;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
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
        Map legMap = Util.mapLegs(response);

        SlimResponse slimResponse = new SlimConverter().createSlimResponse(response, legMap);

        log.info("total search results : "+slimResponse.getSearchResultList().size());
        slimResponse = ruleEngine.process(slimResponse, ruleEngine.readRules());

        return slimResponse;
	}

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public int search() throws Exception {
        return threadManager.executeRules(ruleEngine.readRules());
    }




}
