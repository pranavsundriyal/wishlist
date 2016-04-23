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


}
