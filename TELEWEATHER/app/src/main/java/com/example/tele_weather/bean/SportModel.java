package com.example.tele_weather.bean;

import java.util.List;

public class SportModel {
    private Football football;

    public Football getFootball() {
        return football;
    }

    public static class Football {
        private List<Match> match;

        public List<Match> getMatch() {
            return match;
        }
    }

    public static class Match {
        private String stadium;
        private String country;
        private String tournament;
        private String start;
        private String match;

        public String getStadium() { return stadium; }
        public String getCountry() { return country; }
        public String getTournament() { return tournament; }
        public String getStart() { return start; }
        public String getMatch() { return match; }
    }
}

