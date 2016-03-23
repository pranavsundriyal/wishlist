package com.wishlist.fliter;

import com.wishlist.model.slim.SlimResponse;

import java.util.Map;

public interface Filter {

    SlimResponse filter(SlimResponse slimResponse, Map<String, String> criteriaMap);
}
