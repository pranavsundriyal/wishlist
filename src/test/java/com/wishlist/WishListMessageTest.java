package com.wishlist;

import com.wishlist.email.WishListMessage;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by psundriyal on 3/31/16.
 */
public class WishListMessageTest {

    @Test
    public void testDate(){
        String date = "2016/09/02";
        assertEquals(new WishListMessage().formatDate(date),"09/02/2016");
    }
}
