package com.wishlist.model.slim;

import java.time.LocalDateTime;
import java.util.List;

public class SearchResult {

    String price;

    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    List<com.wishlist.model.Leg> legList;

    public String getPrice() { return price; }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<com.wishlist.model.Leg> getLegList() {
        return legList;
    }

    public void setLegList(List<com.wishlist.model.Leg> legList) {
        this.legList = legList;
    }
}
