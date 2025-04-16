package com.finishherlezlah.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RoastConfig {

    @Value("${ROAST_MAX_INDEX}")
    private int maxIndex;

    public int getMaxIndex() {
        return maxIndex;
    }
    //  Now you’ve got centralized access to your roast guardrail.
}

