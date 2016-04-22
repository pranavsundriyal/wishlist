package com.wishlist.controller;

import com.wishlist.converter.SlimConverter;
import com.wishlist.filter_engine.FileManager;
import com.wishlist.model.Request;
import com.wishlist.model.Response;
import com.wishlist.model.slim.SlimResponse;
import com.wishlist.filter_engine.FilterChainEngine;
import com.wishlist.service.ExpediaSearchServiceImpl;
import com.wishlist.thread.ThreadManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@Component
public class SearchController {

    private Logger log = Logger.getLogger(SearchController.class.getName());

    @Autowired
    private ThreadManager threadManager;

    @Autowired
    private FilterChainEngine filterChainEngine;

    @Autowired
    private FileManager fileManager;

    @Autowired
    private ExpediaSearchServiceImpl expediaSearchService;

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
        Response response = expediaSearchService.execute(request);

        SlimResponse slimResponse = new SlimConverter().createSlimResponse(response);

        log.info("total search results : "+slimResponse.getSearchResultList().size());
        slimResponse = filterChainEngine.process(slimResponse, fileManager.readRules());

        return slimResponse;
	}

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public int search() throws Exception {
        return threadManager.executeRules(fileManager.readRules());
    }

    @RequestMapping(value = "/lookup", method = RequestMethod.GET)
    public List<String> lookUp(@RequestParam(value="email", required=true) String email) throws Exception {
        return fileManager.lookUp(email);
    }

}
