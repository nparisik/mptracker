package com.np.monkeypoxtracker.model;

public class LocationStats {
    private String country;
    private int latestTotalCases;
    private int deaths;

    public LocationStats() {
    }

    public LocationStats(String country, int latestTotalCases, int deaths) {
        this.country = country;
        this.latestTotalCases = latestTotalCases;
        this.deaths = deaths;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "country='" + country + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                ", deaths=" + deaths +
                '}';
    }
}
