package com.example.tele_weather.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SportModel {
    private List<Match> football;

    public List<Match> getFootball() {
        return football;
    }

    public static class Match {
        private String stadium;
        private String country;
        private String tournament;
        private String start;
        @SerializedName("match")
        private String matchName;

        public String getStadium() { return stadium; }
        public String getCountry() { return country; }
        public String getTournament() { return tournament; }
        public String getStart() { return start; }
        public String getMatch() { return matchName; }
    }
}

