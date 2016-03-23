package com.wishlist;

import org.junit.Before;
import org.junit.Test;
import com.wishlist.model.rule.Rule;
import com.wishlist.rule_engine.RuleEngine;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class RuleEngineTest {

    private RuleEngine ruleEngine;

    @Before
    public void setUp(){
        ruleEngine = new RuleEngine();
    }

    @Test
    public void readFileTest() throws IOException{
        List<Rule> rulesList  = ruleEngine.readRule();
        assertNotNull(rulesList);
    }
}
