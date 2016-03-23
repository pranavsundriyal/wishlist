package com.wishlist.model.slim;


import java.util.List;

public class SlimResponse {

    List<SearchResult> searchResultList;

    public List<SearchResult> getSearchResultList() {
        return searchResultList;
    }

    public void setSearchResultList(List<SearchResult> searchResultList) {
        this.searchResultList = searchResultList;
    }

}
