package com.wishlist;

import com.wishlist.filter_engine.FileManager;
import com.wishlist.model.rule.Rule;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.function.BooleanSupplier;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by psundriyal on 4/21/16.
 */
public class FileManagerTest {
    private FileManager fileManager;

    @Before
    public void setUp(){
        fileManager = new FileManager();
    }

    @Test
    public void testGetAllFiles(){
        List<String> files = fileManager.getFiles();
        assertNotNull(files);
    }

    @Test
    public void testReadRules(){
        List<Rule> rules = fileManager.readRules();
        assertNotNull(rules);
    }

    @Test
    public void testSaveRules(){

        List<Rule> ruleList =fileManager.readRules();
        Boolean b = fileManager.saveRule(ruleList.get(0));
    }
}
