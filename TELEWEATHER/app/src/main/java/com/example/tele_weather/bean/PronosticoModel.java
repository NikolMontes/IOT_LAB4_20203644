package com.example.tele_weather.bean;

import java.util.List;

public class PronosticoModel {
    private Forecast forecast;

    public Forecast getForecast() {
        return forecast;
    }

    public static class Forecast {
        private List<ForecastDay> forecastday;

        public List<ForecastDay> getForecastday() {
            return forecastday;
        }
    }

    public static class ForecastDay {
        private String date;
        private Day day;

        public String getDate() {
            return date;
        }

        public Day getDay() {
            return day;
        }
    }

    public static class Day {
        private double maxtemp_c;
        private double mintemp_c;
        private Condition condition;

        public double getMaxtemp_c() { return maxtemp_c; }
        public double getMintemp_c() { return mintemp_c; }
        public Condition getCondition() { return condition; }
    }

    public static class Condition {
        private String text;

        public String getText() {
            return text;
        }
    }
}
