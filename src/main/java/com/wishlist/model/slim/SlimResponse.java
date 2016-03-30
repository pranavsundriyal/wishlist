package com.wishlist.model.slim;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlimResponse {

    private List<SearchResult> searchResultList;

    private Map<String,Integer> filterCountMap = new HashMap<>();

    public List<SearchResult> getSearchResultList() {
        return searchResultList;
    }

    public void setSearchResultList(List<SearchResult> searchResultList) {
        this.searchResultList = searchResultList;
    }

    public Map<String, Integer> getFilterCountMap() {
        return filterCountMap;
    }

    public void setFilterCountMap(Map<String, Integer> filterCountMap) {
        this.filterCountMap = filterCountMap;
    }
}
