package com.wishlist.thread;

import com.wishlist.converter.PriceSort;
import com.wishlist.converter.SlimConverter;
import com.wishlist.model.Request;
import com.wishlist.model.Response;
import com.wishlist.model.slim.SlimResponse;
import com.wishlist.service.ExpediaSearchServiceImpl;
import com.wishlist.util.Util;

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

/**
 * Created by psundriyal on 4/3/16.
 */
public class FlexThreadManager {

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    public SlimResponse getFlexResponses(Request request) {
        List<Response> responses = new ArrayList<>();

        List<Request> requestList = createFlexRequestList(request);

        Set<Callable<Response>> callables = new HashSet<>();
        for (Request r : requestList) {
             callables.add(new ExpediaSearchServiceImpl(r));
        }

        try {
            List<Future<Response>> futures = executorService.invokeAll(callables);

            for (Future f : futures) {
                responses.add((Response) f.get(20L, TimeUnit.SECONDS));
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return merge(responses);
    }

    private SlimResponse merge(List<Response> responses) {
        SlimResponse aggregateResponse = new SlimResponse();
        aggregateResponse.setSearchResultList(new ArrayList<>());
        for (Response response : responses){
            SlimResponse slimResponse = new SlimConverter().createSlimResponse(response);
            aggregateResponse.getSearchResultList().addAll(slimResponse.getSearchResultList());
        }

        PriceSort priceSort = new PriceSort();
        Collections.sort(aggregateResponse.getSearchResultList(), priceSort);
        return aggregateResponse;
    }

    public List<Request> createFlexRequestList(Request request) {
        List<Request> requests = new ArrayList<>();

        requests.add(request);

        Request clone1 = Request.cloneRequest(request);
        clone1.setArrivalDate(Util.addDate(request.getArrivalDate(),-1));
        clone1.setDeparturteDate(Util.addDate(request.getDeparturteDate(),-1));

        Request clone2 = Request.cloneRequest(request);
        clone2.setArrivalDate(Util.addDate(request.getArrivalDate(),-1));

        Request clone3 = Request.cloneRequest(request);
        clone3.setArrivalDate(Util.addDate(request.getArrivalDate(),-1));
        clone3.setDeparturteDate(Util.addDate(request.getDeparturteDate(),1));

        Request clone4 = Request.cloneRequest(request);
        clone4.setArrivalDate(Util.addDate(request.getArrivalDate(),1));

        Request clone5 = Request.cloneRequest(request);
        clone5.setArrivalDate(Util.addDate(request.getArrivalDate(),1));
        clone5.setDeparturteDate(Util.addDate(request.getDeparturteDate(),1));

        Request clone6 = Request.cloneRequest(request);
        clone6.setArrivalDate(Util.addDate(request.getArrivalDate(),1));

        Request clone7 = Request.cloneRequest(request);
        clone7.setArrivalDate(Util.addDate(request.getArrivalDate(),-1));
        clone7.setDeparturteDate(Util.addDate(request.getDeparturteDate(),1));

        Request clone8 = Request.cloneRequest(request);
        clone8.setDeparturteDate(Util.addDate(request.getDeparturteDate(),-1));

        requests.add(clone1);
        requests.add(clone2);
        requests.add(clone3);
        requests.add(clone4);
        requests.add(clone5);
        requests.add(clone6);
        requests.add(clone7);
        requests.add(clone8);

        return requests;
    }


}
