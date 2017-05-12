package com.wishlist;

import com.wishlist.filter_engine.FileManager;
import com.wishlist.model.rule.Rule;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.net.URL;
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
        fileManager.setFilePath("/Users/psundriyal/expedia/wishlist/src/test/resources");
    }


    @Test
    public void testDeleteRule(){
        List<Rule> ruleList = fileManager.deleteRule("psundriyal@orbitz.com", 10);
        assertNotNull(ruleList);
    }

}
