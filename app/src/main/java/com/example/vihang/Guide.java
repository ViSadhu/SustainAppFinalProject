package com.example.vihang;

public enum Guide {
    GENERAL_RECYCLING("https://www.epa.gov/recycle/frequent-questions-recycling#recycling101"),
    SDG("https://sdgs.un.org/"),
    ACCEPTABLE_ITEMS("https://www.epa.gov/recycle/how-do-i-recycle-common-recyclables"),
    PLASTICS("https://www.bpf.co.uk/plastipedia/sustainability/how-is-plastic-recycled-a-step-by-step-guide-to-recycling.aspx");
    private final String url;
    Guide(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}
