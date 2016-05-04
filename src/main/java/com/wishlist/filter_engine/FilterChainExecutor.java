package com.wishlist.filter_engine;

import com.wishlist.converter.SlimConverter;
import com.wishlist.email.Email;
import com.wishlist.email.WishListMessage;
import com.wishlist.model.Request;
import com.wishlist.model.Response;
import com.wishlist.model.rule.Rule;
import com.wishlist.model.slim.SlimResponse;

import com.wishlist.service.ExpediaSearchServiceImpl;
import com.wishlist.thread.FlexThreadManager;
import com.wishlist.util.Util;
import net.sf.ehcache.CacheManager;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class FilterChainExecutor implements Runnable {


    private Rule rule;
    private Email email;
    private FlexThreadManager flexThreadManager;
    private CacheManager cacheManager;
    private int intervalPeriod;

    private Logger log = Logger.getLogger(this.getClass().getName());

    public FilterChainExecutor(Rule rule, Email email, FlexThreadManager flexThreadManager, int intervalPeriod, CacheManager cacheManager) {
        this.rule = rule;
        this.email = email;
        this.flexThreadManager = flexThreadManager;
        this.intervalPeriod = intervalPeriod;
        this.cacheManager =cacheManager;
    }

    private void execute() {

        log.info("executing rule : " + rule.toString());
        Request request = Util.creatRequestFromRule(rule);
        SlimResponse slimResponse;
        int flexDays = Integer.parseInt(rule.getFlex());
        if (flexDays > 0) {
            slimResponse = flexThreadManager.getFlexResponses(request, flexDays);
        } else {
            Response response = new ExpediaSearchServiceImpl(request,cacheManager).execute();
            slimResponse = SlimConverter.createSlimResponse(response);
        }
        log.info(WishListMessage.createSubject(rule) +
                " search results before operating filter engine : " + slimResponse.getSearchResultList().size() + "\n");

        FilterChainEngine filterChainEngine = new FilterChainEngine();
        slimResponse = filterChainEngine.processCritera(slimResponse, rule.getFilters());

        if (slimResponse.getSearchResultList().size() > 0) {
            email.sendMail(slimResponse, rule);
            doLogging(slimResponse);
        }
    }


    private void executePeriodically(){
        while (true) {
            execute();
            try {
                TimeUnit.HOURS.sleep(intervalPeriod);
            } catch (InterruptedException exception) {
            }
        }
    }

    @Override
    public void run() {
        if (intervalPeriod > 0) {
            executePeriodically();
        } else {
            execute();
        }
        log.info("Thread executed successfully");
    }

    private void doLogging(SlimResponse slimResponse) {
        log.info(WishListMessage.createSubject(rule));
        log.info("total search results after rule engine processing: " + slimResponse.getSearchResultList().size());
        log.info(WishListMessage.createMessage(rule,slimResponse));
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
}
