package com.wishlist;

import com.wishlist.compression.CompressionUtil;
import com.wishlist.model.Leg;
import com.wishlist.model.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by psundriyal on 5/6/16.
 */
public class CompressionUtilTest {

    @Test
    public void compressionTest(){
        Response response = new Response();
        Leg leg = new Leg();
        leg.setLegId("asd");
        List <Leg> legs = new ArrayList<>();
        legs.add(leg);
        response.setLegs(legs);
        byte [] as = CompressionUtil.compress(response);
        Response response1 = CompressionUtil.decompress(as);
        assertEquals(response.getLegs(), response1.getLegs());
    }
}
