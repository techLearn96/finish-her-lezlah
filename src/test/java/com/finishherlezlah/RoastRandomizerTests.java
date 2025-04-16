package com.finishherlezlah;


import com.finishherlezlah.error.RoastExceptionHandler;
import com.finishherlezlah.security.LezlahRateLimiter;
import com.finishherlezlah.util.RoastRandomizer;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


class RoastRandomizerTest {

    @Test
    void validate_Category_Forehead_Success_IndexInBounds() {
        // simple but 💪
        String category = "FOREHEAD";
        int index = 0;
        assertEquals(RoastRandomizer.getRoast(category, index),
                "AriHell, your forehead is shaped like an almond.");
    }

    @Test()
    void validateCategoryForeheadSuccessIndexOutOfBounds() {
        // simple but 💪
        String category = "FOREHEAD";
        int index = 66;
        assertThrows(RoastExceptionHandler.class, () -> {
           RoastRandomizer.getRoast(category, index);
        });
    }

    @Test()
    void validateCategoryRoastAlreadyUsed(){

    }




}
