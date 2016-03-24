package com.wishlist;

import org.junit.Before;
import org.junit.Test;
import com.wishlist.model.rule.Rule;
import com.wishlist.filter_engine.FilterChainEngine;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class FilterChainEngineTest {

    private FilterChainEngine filterChainEngine;

    @Before
    public void setUp(){
        filterChainEngine = new FilterChainEngine();
    }

    @Test
    public void readFileTest() throws IOException{
        List<Rule> rulesList  = filterChainEngine.readRules();
        assertNotNull(rulesList);
    }
}
