package com.wishlist.filter_engine;

import com.wishlist.controller.SearchController;
import com.wishlist.converter.SlimConverter;
import com.wishlist.email.Email;
import com.wishlist.email.WishListMessage;
import com.wishlist.filter_engine.FilterChainEngine;
import com.wishlist.model.Request;
import com.wishlist.model.Response;
import com.wishlist.model.rule.Rule;
import com.wishlist.model.slim.SearchResult;
import com.wishlist.model.slim.SlimResponse;

import com.wishlist.service.ExpediaSearchServiceImpl;
import com.wishlist.thread.FlexThreadManager;
import com.wishlist.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by psundriyal on 3/23/16.
 */


@Component
public class FilterChainExecutor implements Runnable {


    private Rule rule;

    @Autowired
    private FlexThreadManager flexThreadManager;

    @Autowired
    private ExpediaSearchServiceImpl expediaSearchService;

    private Logger log = Logger.getLogger(this.getClass().getName());

    private void execute(Rule rule){
        while (true) {
            Request request = Util.creatRequestFromRule(rule);
            SlimResponse slimResponse = null;
            if (rule.getFlex()){
                slimResponse = flexThreadManager.getFlexResponses(request);
            } else {
                Response response = expediaSearchService.execute(request);
                slimResponse = new SlimConverter().createSlimResponse(response);
            }
            log.info(WishListMessage.createSubject(rule)+
                    " search results before operating filter engine : "+slimResponse.getSearchResultList().size()+"\n");

            FilterChainEngine filterChainEngine = new FilterChainEngine();
            slimResponse = filterChainEngine.processCritera(slimResponse, rule.getFilters());

            if (slimResponse.getSearchResultList().size()>0){
                //new Email().sendMail(slimResponse, rule);
                doLogging(slimResponse);
            }

            try {
                TimeUnit.HOURS.sleep(24);
            } catch (InterruptedException exception) {
            }
        }
    }

    @Override
    public void run() {
        execute(this.rule);
        log.info("Thread executed successfully");
    }

    private void doLogging(SlimResponse slimResponse) {
        log.info(WishListMessage.createSubject(rule));
        log.info("total search results after rule engine processing: " + slimResponse.getSearchResultList().size());
        log.info(WishListMessage.createMessageAll(rule,slimResponse));
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}
