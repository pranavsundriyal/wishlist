package com.wishlist.filter_engine;

import com.wishlist.controller.SearchController;
import com.wishlist.converter.SlimConverter;
import com.wishlist.email.Email;
import com.wishlist.model.Request;
import com.wishlist.model.Response;
import com.wishlist.model.rule.Rule;
import com.wishlist.model.slim.SearchResult;
import com.wishlist.model.slim.SlimResponse;

import com.wishlist.service.ExpediaSearchServiceImpl;
import com.wishlist.util.Util;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by psundriyal on 3/23/16.
 */


public class FilterChainExecutor implements Runnable {


    private Rule rule;

    public FilterChainExecutor(Rule rule){
        this.rule= rule;
    }

    private Logger log = Logger.getLogger(SearchController.class.getName());

    private void execute(){
        while (true) {
            Request request = Util.creatRequestFromRule(rule);
            Response response = new ExpediaSearchServiceImpl().execute(request);
            Map legMap = Util.mapLegs(response);

            SlimResponse slimResponse = new SlimConverter().createSlimResponse(response, legMap);
            FilterChainEngine filterChainEngine = new FilterChainEngine();
            slimResponse = filterChainEngine.processCritera(slimResponse, rule.getFilters());

            if (slimResponse.getSearchResultList().size()>0){
                new Email().sendMail(slimResponse, rule);
            }

            doLogging(slimResponse);
            try {
                TimeUnit.HOURS.sleep(6);
            } catch (InterruptedException exception) {
            }
        }
    }

    @Override
    public void run() {
        execute();
        log.info("Thread executed successfully");
    }

    private void doLogging(SlimResponse slimResponse) {
        log.info(Util.createSubject(rule));
        log.info("total search results after rule engine processing: " + slimResponse.getSearchResultList().size());
        StringBuffer sb = new StringBuffer();
        for (SearchResult searchResult : slimResponse.getSearchResultList()){
            sb.append(searchResult.getPrice()).append("\n");
        }
        log.info(sb.toString());
    }


}
