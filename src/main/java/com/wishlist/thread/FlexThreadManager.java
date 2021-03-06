package com.wishlist.thread;

import com.wishlist.converter.PriceSort;
import com.wishlist.converter.SlimConverter;
import com.wishlist.model.Request;
import com.wishlist.model.Response;
import com.wishlist.model.slim.SlimResponse;
import com.wishlist.service.ExpediaSearchServiceImpl;
import com.wishlist.util.Util;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

/**
 * Created by psundriyal on 4/3/16.
 */
@Component
public class FlexThreadManager {

    private Logger log = Logger.getLogger(this.getClass().getName());


    @Autowired
    private ExecutorService flexExecutorService;

    @Autowired
    CacheManager cacheManager;

    public SlimResponse getFlexResponses(Request request, int flexDays) {
        List<Response> responses = new ArrayList<>();

        List<Request> requestList = createFlexRequests(request, flexDays-1);

        Set<Callable<Response>> callables = new HashSet<>();
        for (Request r : requestList) {
             callables.add(new ExpediaSearchServiceImpl(r,cacheManager));
        }

        try {
            List<Future<Response>> futures = flexExecutorService.invokeAll(callables, 50L,TimeUnit.SECONDS);

            for (Future f : futures) {
                responses.add((Response) f.get());
            }
        } catch (InterruptedException e){
            log.info(e.getMessage());
        } catch (ExecutionException e) {
            log.info(e.getMessage());
        }

        return merge(responses);
    }

    private SlimResponse merge(List<Response> responses) {
        SlimResponse aggregateResponse = new SlimResponse();
        aggregateResponse.setSearchResultList(new ArrayList<>());
        for (Response response : responses){
            SlimResponse slimResponse = SlimConverter.createSlimResponse(response);
            aggregateResponse.getSearchResultList().addAll(slimResponse.getSearchResultList());
        }

        Collections.sort(aggregateResponse.getSearchResultList(), new PriceSort());
        return aggregateResponse;
    }


    public List<Request> createFlexRequests(Request request, int flexDays) {
        List<Request> requests = new ArrayList<>();

        for (int i=-flexDays; i<=flexDays;i++){
            for (int j=-flexDays; j<=flexDays; j++){
                Request clone = Request.cloneRequest(request);
                if(!request.getArrivalDate().equals("")) {
                    clone.setArrivalDate(Util.addDate(request.getArrivalDate(), i));
                }
                clone.setDeparturteDate(Util.addDate(request.getDeparturteDate(),j));
                requests.add(clone);
            }
        }
        return requests;
    }


}
