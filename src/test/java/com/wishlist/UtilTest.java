package com.wishlist;

import com.wishlist.filter_engine.FilterChainEngine;
import com.wishlist.model.rule.Rule;
import com.wishlist.util.Util;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;


public class UtilTest {


    @Test
    public void parseDuration() throws IOException {
        LocalTime time =  Util.parseDuration("PT8H15M");
        assertEquals(time.getHour(), 8);
        assertEquals(time.getMinute(), 15);
    }

    @Test
    public void parseDurationH() throws IOException {
        LocalTime time =  Util.parseDuration("PT8H");
        assertEquals(time.getHour(), 8);
        assertEquals(time.getMinute(), 0);

    }

    @Test
    public void parseDurationDH() throws IOException {
        LocalTime time =  Util.parseDuration("P1DT55M");
        assertEquals(time.getHour(), 0);
        assertEquals(time.getMinute(), 55);

    }

    @Test
    public void parseDurationM() throws IOException {
        LocalTime time =  Util.parseDuration("PT15M");
        assertEquals(time.getHour(), 0);
        assertEquals(time.getMinute(), 15);

    }

    @Test
    public  void testAddDate() {
        assertEquals(Util.addDate("2016-04-20", 1),"2016-04-21");
        assertEquals(Util.addDate("2016-04-01", -1),"2016-03-31");
        assertEquals(Util.addDate("2016-04-1", 1),"2016-04-02");
    }

    @Test
    public void testaddLocalTime() {
        LocalTime t1 = LocalTime.of(12,20,0,0);
        LocalTime t2 = LocalTime.of(17,34,0,0);
        assertEquals(Util.addLocalTime(t1,t2), LocalTime.of(19,54,0,0));
    }
}
