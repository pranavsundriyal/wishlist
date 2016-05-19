package com.wishlist.controller;

import com.wishlist.model.Request;
import com.wishlist.model.slim.SlimResponse;
import com.wishlist.trends.TrendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by psundriyal on 5/15/16.
 */

@RestController
@Component
public class TrendController {

    @Autowired
    TrendServiceImpl trendService;

    @RequestMapping(value = "/trend", method = RequestMethod.GET)
    public String search(@RequestParam(value = "origin", required = true) String origin,
                               @RequestParam(value = "dest", required = true) String destination,
                               @RequestParam(value = "departure", required = true) String departureDate)
            throws Exception {

        return trendService.executeCall(new Request(departureDate, origin, destination));

    }
}