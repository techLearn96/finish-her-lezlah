package com.finishherlezlah.error;

// RoastExceptionHandler = Blueprint or Skeleton
public class RoastExceptionHandler extends RuntimeException {

    // instances / characteristics of RoastExceptionHandler
    private final String category;
    private final int index;

    // constructor for initializing new RoastExceptionHandler objects
    public RoastExceptionHandler(String category, int index) {
        super("Invalid roast request for category: " + category + ", index: " + index);
        this.category = category;
        this.index = index;
    }

    // behaviors
    public String getCategory() {
        return category;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String getLocalizedMessage() {
        return "Category '" + category + "' with index " + index + " is invalid. No roast found.";
    }



}






