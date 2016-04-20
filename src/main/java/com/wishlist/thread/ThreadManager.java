package com.wishlist.thread;

import com.wishlist.model.rule.Rule;
import com.wishlist.filter_engine.FilterChainExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by psundriyal on 3/23/16.
 */

@Component
public class ThreadManager {

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Autowired
    private FilterChainExecutor filterChainExecutor;
    public int executeRules(List<Rule> ruleList){

        for (Rule rule: ruleList){
            filterChainExecutor.setRule(rule);
            executorService.execute(filterChainExecutor);
        }
        return ruleList.size();
    }
}
