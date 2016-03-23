package com.wishlist.converter;

import com.wishlist.model.slim.SearchResult;

import java.util.Comparator;

public class PriceSort implements Comparator<SearchResult> {


    public PriceSort() {
    }

    @Override
    public int compare(SearchResult o1, SearchResult o2) {

        float price1 = Float.parseFloat(o1.getPrice());
        float price2 = Float.parseFloat(o2.getPrice());

        if (price1 < price2)
            return -1;
        if (price1 > price2)
            return 1;
        return 0;
    }
}


