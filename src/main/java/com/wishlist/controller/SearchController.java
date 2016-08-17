package com.wishlist.controller;

import com.wishlist.converter.SlimConverter;
import com.wishlist.email.Email;
import com.wishlist.filter_engine.FileManager;
import com.wishlist.model.Request;
import com.wishlist.model.Response;
import com.wishlist.model.rule.Rule;
import com.wishlist.model.slim.SlimResponse;
import com.wishlist.filter_engine.FilterChainEngine;
import com.wishlist.service.ExpediaSearchServiceImpl;
import com.wishlist.thread.FlexThreadManager;
import com.wishlist.thread.SingleThreadManager;
import com.wishlist.thread.ThreadManager;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

@RestController
@Component
public class SearchController {

    private Logger log = Logger.getLogger(SearchController.class.getName());

    @Autowired
    private ThreadManager threadManager;

    @Autowired
    private FilterChainEngine filterChainEngine;

    @Autowired
    private FileManager fileManager;

    @Autowired
    private SlimConverter slimConverter;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private ExecutorService rulesExecutorService;

    @Autowired
    private FlexThreadManager flexThreadManager;

    @Autowired
    private Email email;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
	public SlimResponse search(@RequestParam(value="origin", required=true) String origin,
                               @RequestParam(value="dest", required=true) String destination,
                               @RequestParam(value="departure", required=true) String departureDate,
                               @RequestParam(value="arrival", required=false) String arrivalDate) throws Exception {

        Request request;
        if (arrivalDate == null) {
             request = new Request(departureDate, origin, destination);
        } else {
             request = new Request(arrivalDate,departureDate, origin, destination);
        }
        Response response = new ExpediaSearchServiceImpl(request,cacheManager).execute();

        SlimResponse slimResponse = slimConverter.createSlimResponse(response);

        log.info("total search results : "+slimResponse.getSearchResultList().size());
        slimResponse = filterChainEngine.process(slimResponse, fileManager.readRules());

        return slimResponse;
	}

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public int search() throws Exception {
        log.info("Executing all rules");
        return threadManager.executeRules(fileManager.readRules(),0);
    }

    @RequestMapping(value = "/job", method = RequestMethod.GET)
    public String search(@RequestParam(value="interval", required=true) String period) throws Exception {
        log.info("Executing all rules");
        SingleThreadManager singleThreadManager = new SingleThreadManager(flexThreadManager, email, cacheManager,
                fileManager, Integer.parseInt(period));
        rulesExecutorService.execute(singleThreadManager);
        return "successfully started";
    }

    @RequestMapping(value = "/lookup", method = RequestMethod.GET)
    public List<String> lookUp(@RequestParam(value="email", required=true) String email) throws Exception {
        return fileManager.lookUp(email);
    }

    @RequestMapping(value = "/unsubscribe", method = RequestMethod.GET)
    public Boolean unsubscribe(@RequestParam(value="email", required=true) String email) throws Exception {
        return fileManager.delete(email);
    }

    @RequestMapping(value = "/deleteRule", method = RequestMethod.GET)
    public List<Rule> deleteRule(@RequestParam(value="email", required=true) String email,
                                 @RequestParam(value ="ruleNo", required = true) String ruleNo) throws Exception {
        return fileManager.deleteRule(email, Integer.parseInt(ruleNo)-1);
    }
}
