package com.wishlist.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wishlist.converter.SlimConverter;
import com.wishlist.filter_engine.FilterChainHelper;
import com.wishlist.model.Request;
import com.wishlist.model.Response;
import com.wishlist.model.slim.SlimResponse;
import com.wishlist.filter_engine.FilterChainEngine;
import com.wishlist.service.ExpediaSearchServiceImpl;
import com.wishlist.thread.ThreadManager;
import com.wishlist.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private FilterChainEngine filterChainEngine;

    @Autowired
    private FilterChainHelper filterChainHelper;

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

        SlimResponse slimResponse = new SlimConverter().createSlimResponse(response);

        log.info("total search results : "+slimResponse.getSearchResultList().size());
        slimResponse = filterChainEngine.process(slimResponse, filterChainHelper.readRules());

        return slimResponse;
	}

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public int search() throws Exception {
        return threadManager.executeRules(filterChainHelper.readRules());
    }


    @RequestMapping(value = "/")
    public Model greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return model;
    }

}
