package com.wishlist.controller;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.statistics.StatisticsGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by psundriyal on 5/4/16.
 */

@RestController
@Component
public class CacheController {

    @Autowired
    private CacheManager cacheManager;

    @RequestMapping(value = "/cacheStats", method = RequestMethod.GET)
    public String stats() {
        StringBuffer sb = new StringBuffer();

    /* get stats for all known caches */
        for (String name : cacheManager.getCacheNames()) {
            Cache cache = cacheManager.getCache(name);
            StatisticsGateway statisticsGateway = cache.getStatistics();
            sb.append(getCacheStats(statisticsGateway)+"\n\n");
        }
        return sb.toString();
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public List<StatisticsGateway> stat() {
        List<StatisticsGateway> statisticsGateways = new ArrayList<>();
        for (String name : cacheManager.getCacheNames()) {
            Cache cache = cacheManager.getCache(name);
            StatisticsGateway statisticsGateway = cache.getStatistics();
            statisticsGateways.add(statisticsGateway);
        }
        return statisticsGateways;
    }
    public String getCacheStats(StatisticsGateway statisticsGateway){
        StringBuffer sb = new StringBuffer();
        sb.append("local disk size in bytes: " + statisticsGateway.getLocalDiskSizeInBytes()+"\n");
        sb.append("local disk size: " + statisticsGateway.getLocalDiskSize()+"\n");
        sb.append("remote size: " + statisticsGateway.getRemoteSize()+"\n");
        sb.append("local heap size: "+ statisticsGateway.getLocalHeapSize()+"\n");
        sb.append("local heap size in bytes: " + statisticsGateway.getLocalHeapSizeInBytes()+"\n");

        return sb.toString();
    }
}
