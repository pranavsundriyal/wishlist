package com.wishlist;

import com.wishlist.model.Request;
import com.wishlist.service.ExpediaSearchServiceImpl;
import com.wishlist.thread.FlexThreadManager;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by psundriyal on 4/3/16.
 */
public class FlexThreadManagerTest {

    private FlexThreadManager flexThreadManager;



    @Before
    public void setUp() {
        flexThreadManager = new FlexThreadManager();
    }

    @Test
    public void testFlexManager() throws InterruptedException, ExecutionException, TimeoutException {
        Request request = new Request("2016-05-02", "2016-04-20", "CHI", "LAS");
        assertNotNull(flexThreadManager.getFlexResponses(request));
    }

    @Test
    public void testcreateFlexRequestList() {
        Request request = new Request("2016-05-02", "2016-04-20", "CHI", "BOM");
        assertEquals(flexThreadManager.createFlexRequestList(request).size(),9);
    }
}
