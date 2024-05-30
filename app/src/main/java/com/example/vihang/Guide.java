package com.example.vihang;

public enum Guide {
    GENERAL_RECYCLING("https://www.epa.gov/recycle/frequent-questions-recycling#recycling101"),
    SDG("https://www.un.org/sustainabledevelopment/"),
    ACCEPTABLE_ITEMS("https://www.epa.gov/recycle/how-do-i-recycle-common-recyclables"),
    PLASTICS("https://learn.eartheasy.com/articles/plastics-by-the-numbers/");
    private final String url;
    Guide(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}
