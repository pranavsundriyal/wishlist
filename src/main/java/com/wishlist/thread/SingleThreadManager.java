package com.wishlist.thread;

import com.wishlist.email.Email;
import com.wishlist.filter_engine.FileManager;
import com.wishlist.filter_engine.FilterChainExecutor;
import com.wishlist.model.rule.Rule;
import net.sf.ehcache.CacheManager;

import java.util.List;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by psundriyal on 8/14/16.
 */


public class SingleThreadManager implements Runnable {

    private FlexThreadManager flexThreadManager;

    private Email email;

    private CacheManager cacheManager;

    private FileManager fileManager;

    private int interval;

    private Logger log = Logger.getLogger(this.getClass().getName());

    public SingleThreadManager(FlexThreadManager flexThreadManager, Email email, CacheManager cacheManager,
                               FileManager fileManager, int interval) {
        this.flexThreadManager = flexThreadManager;
        this.email = email;
        this.cacheManager =cacheManager;
        this.fileManager = fileManager;
        this.interval = interval;
    }



    public void executeRules() {

        if (interval > 0) {
            while (true) {
                List<Rule> ruleList = fileManager.readRules();
                for (Rule rule : ruleList) {
                    FilterChainExecutor filterChainExecutor = new FilterChainExecutor(rule, email, flexThreadManager, cacheManager);
                    filterChainExecutor.execute();
                }
                try {
                    TimeUnit.HOURS.sleep(interval);
                } catch (InterruptedException exception) {
                }
                log.info("Thread executed successfully");
            }
        } else {
            List<Rule> ruleList = fileManager.readRules();
            for (Rule rule : ruleList) {
                FilterChainExecutor filterChainExecutor = new FilterChainExecutor(rule, email, flexThreadManager, cacheManager);
                filterChainExecutor.execute();
            }
        }
    }



    @Override
    public void run() {
        executeRules();
    }
}
