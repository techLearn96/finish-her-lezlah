package com.finishherlezlah.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.finishherlezlah.error.RateLimitException;
import com.finishherlezlah.error.RoastErrorCodes;
import com.finishherlezlah.security.LezlahRateLimiter;
import com.finishherlezlah.util.RoastRandomizer;
import com.finishherlezlah.constants.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(CommonConstants.BASE_API + CommonConstants.ROAST_RESOURCE)
public class RoastController {
    // http://localhost:8080//api/v1/roast/forehead/1

    private final RoastRandomizer roastRandomizer;
    private final LezlahRateLimiter lezlahRateLimiter;
    private AtomicInteger requestCounter = new AtomicInteger(0); // Add at the top of the controller
    private static final Logger log = LoggerFactory.getLogger(RoastController.class);


    @Autowired
    public RoastController(RoastRandomizer roastRandomizer, LezlahRateLimiter lezlahRateLimiter) {
        this.roastRandomizer = roastRandomizer;
        this.lezlahRateLimiter = lezlahRateLimiter;
    }

    @GetMapping(CommonConstants.CATEGORY_ROAST_API)
    public ResponseEntity<String> getRoast(@PathVariable String category) {
        return ResponseEntity.ok(roastRandomizer.getNextRoast(category));
    }

    @GetMapping(CommonConstants.ROAST_RESOURCE)
    public String getRoastLimit(@RequestParam String category){

        if (lezlahRateLimiter.shouldAllowRequest()) {
            log.info("🔥 Total roast requests so far: {}", requestCounter.incrementAndGet());

            return roastRandomizer.getNextRoast(category);
        }
        throw new RateLimitException(
                RoastErrorCodes.RATE_LIMIT_REACHED,
                CommonConstants.REQUEST_LIMIT_ERROR,
                CommonConstants.REQUEST_LIMIT_ERROR_DESCRIPTION

        );

    }
}

