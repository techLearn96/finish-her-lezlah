package com.finishherlezlah.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "lezlah.ratelimit")
public class LezlahRateLimitProperties {
  // POJO :: Plain Old Java Object


    private int maxRequests;
    private int cooldownSeconds;

    // Getters
    public int getMaxRequests() {
        return maxRequests;
    }

    public int getCooldownSeconds() {
        return cooldownSeconds;
    }

    // Setters
    public void setMaxRequests(int maxRequests) {
        this.maxRequests = maxRequests;
    }

    public void setCooldownSeconds(int cooldownSeconds) {
        this.cooldownSeconds = cooldownSeconds;
    }

}
