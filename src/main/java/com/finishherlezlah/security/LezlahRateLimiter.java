package com.finishherlezlah.security;

import com.finishherlezlah.config.LezlahRateLimitProperties;
import com.finishherlezlah.constants.CommonConstants;
import com.finishherlezlah.error.RateLimitException;
import com.finishherlezlah.error.RoastErrorCodes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LezlahRateLimiter {

    private final LezlahRateLimitProperties config;

    private int requestCount = 0;
    private long lastRequestTimestamp = 0;

    private static final Logger log = LoggerFactory.getLogger(LezlahRateLimiter.class);

    @Autowired
    public LezlahRateLimiter(LezlahRateLimitProperties config) {
        this.config = config;
    }

    public boolean shouldAllowRequest() {

        log.info("🧨 WE ARE INSIDE shouldAllowRequest()");
        log.info("🧮 Current config → Max: {}, Cooldown: {}", config.getMaxRequests(), config.getCooldownSeconds());

        long now = System.currentTimeMillis();
        long elapsedTime = now - lastRequestTimestamp;

        boolean isCooldownOver = elapsedTime >= config.getCooldownSeconds() * 1000L;
        long timeLeft = (config.getCooldownSeconds() * 1000L - elapsedTime) / 1000L;

        if (this.requestCount >= config.getMaxRequests()) {
            if (!isCooldownOver) {
                log.warn("🧊 Lezlah needs space. You may approach again in {} seconds.", timeLeft);
                throw new RateLimitException(
                        RoastErrorCodes.RATE_LIMIT_REACHED,
                        CommonConstants.REQUEST_LIMIT_ERROR,
                        CommonConstants.REQUEST_LIMIT_ERROR_DESCRIPTION
                );
            } else {
                // Cooldown is over → reset count
                log.info("⏱️ Cooldown over. Resetting requestCount.");
                this.requestCount = 0;
            }
        }

        this.requestCount++;
        lastRequestTimestamp = now;
        return true;
    }

}
