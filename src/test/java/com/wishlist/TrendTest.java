package com.wishlist;

import com.wishlist.model.trends.TrendResponse;
import com.wishlist.trends.TrendServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by psundriyal on 5/29/16.
 */
public class TrendTest {

    TrendServiceImpl trendService;

    @Before
    public void setUp(){
        trendService = new TrendServiceImpl();
    }

    @Test
    public void testreadJson(){
        TrendResponse response = trendService.readJson();
        assertNotNull(response);
    }
}
