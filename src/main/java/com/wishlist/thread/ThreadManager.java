package com.wishlist.thread;

import com.wishlist.converter.SlimConverter;
import com.wishlist.email.Email;
import com.wishlist.filter_engine.FileManager;
import com.wishlist.model.rule.Rule;
import com.wishlist.filter_engine.FilterChainExecutor;
import com.wishlist.service.ExpediaSearchServiceImpl;
import net.sf.ehcache.CacheManager;
import org.hibernate.validator.constraints.LuhnCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Created by psundriyal on 3/23/16.
 */

@Component
public class ThreadManager {

    @Autowired
    private ExecutorService rulesExecutorService;
    @Autowired
    private FlexThreadManager flexThreadManager;
    @Autowired
    private Email email;
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private FileManager fileManager;

    private Logger log = Logger.getLogger(this.getClass().getName());

    public int executeRules(List<Rule> ruleList, int intervalPeriod){

        for (Rule rule: ruleList){
            FilterChainExecutor filterChainExecutor = new FilterChainExecutor(rule, email, flexThreadManager, intervalPeriod, cacheManager);
            rulesExecutorService.execute(filterChainExecutor);
        }
        return ruleList.size();
    }
}
