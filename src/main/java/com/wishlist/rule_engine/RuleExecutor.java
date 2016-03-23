package com.wishlist.rule_engine;

import com.wishlist.controller.SearchController;
import com.wishlist.converter.SlimConverter;
import com.wishlist.email.Email;
import com.wishlist.model.Request;
import com.wishlist.model.Response;
import com.wishlist.model.rule.Rule;
import com.wishlist.model.slim.SlimResponse;

import com.wishlist.service.ExpediaSearchServiceImpl;
import com.wishlist.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by psundriyal on 3/23/16.
 */
public class RuleExecutor implements Runnable {


    private Rule rule;

    public RuleExecutor(Rule rule){
        this.rule= rule;
    }

    private Logger log = Logger.getLogger(SearchController.class.getName());

    private void execute(){
        while (true) {
            Request request = Util.creatRequestFromRule(rule);
            Response response = new ExpediaSearchServiceImpl().execute(request);
            Map legMap = Util.mapLegs(response);

            SlimResponse slimResponse = new SlimConverter().createSlimResponse(response, legMap);
            log.info("total search results : " + slimResponse.getSearchResultList().size());

            RuleEngine ruleEngine = new RuleEngine();
            slimResponse = ruleEngine.processCritera(slimResponse, rule.getCriterias());
            log.info("total search results after rule engine processing: " + slimResponse.getSearchResultList().size());
            log.info(slimResponse.toString());
            if (slimResponse.getSearchResultList().size()>0){
                new Email().sendMail(slimResponse, rule);
            }
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


}
