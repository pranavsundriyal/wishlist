package com.wishlist.thread;

import com.wishlist.model.rule.Rule;
import com.wishlist.rule_engine.RuleExecutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by psundriyal on 3/23/16.
 */

@Component
public class ThreadManager {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    public int executeRules(List<Rule> ruleList){

        for (Rule rule: ruleList){
            RuleExecutor ruleExecutor = new RuleExecutor(rule);
            executorService.execute(ruleExecutor);
        }
        return ruleList.size();
    }
}
