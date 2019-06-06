package com.example.notweather.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WeatherTest {

    @Test
    public void testThunderstormMightBeRain() {
        Weather weatherRain =
                new Weather(200, "Thunderstorm", "thunderstorm with light rain", "11d");
        assertEquals(Weather.RAIN, weatherRain.getRainNotRain());

        Weather weatherDrizzle =
                new Weather(210, "Thunderstorm", "thunderstorm with light drizzle", "11d");
        assertEquals(Weather.RAIN, weatherDrizzle.getRainNotRain());

        Weather weatherNotRain = new Weather(230, "Thunderstorm", "light thunderstorm", "11d");
        assertEquals(Weather.NOT_RAIN, weatherNotRain.getRainNotRain());
    }

    @Test
    public void testDrizzleIsRain() {
        Weather weather = new Weather(300, "Drizzle", "light intensity drizzle", "09d");
        assertEquals(Weather.RAIN, weather.getRainNotRain());
    }

    @Test
    public void testRainIsRain() {
        Weather weather = new Weather(500, "Rain", "light rain", "10d");
        assertEquals(Weather.RAIN, weather.getRainNotRain());
    }

    @Test
    public void testSnowIsNotRain() {
        Weather weather = new Weather(600, "Snow", "light snow", "13d");
        assertEquals(Weather.NOT_RAIN, weather.getRainNotRain());
    }

    @Test
    public void testClearIsNotRain() {
        Weather weather = new Weather(800, "Clear", "clear sky", "01n");
        assertEquals(Weather.NOT_RAIN, weather.getRainNotRain());
    }

    @Test
    public void testCloudsIsNotRain() {
        Weather weather = new Weather(801, "Clouds", "few clouds: 11-25%", "02n");
        assertEquals(Weather.NOT_RAIN, weather.getRainNotRain());
    }

    @Test
    public void testIconUrlFormat() {
        Weather weather = new Weather(801, "Clouds", "few clouds: 11-25%", "02n");
        assertEquals("https://api.openweathermap.org/img/w/02n.png", weather.getIconUrl());
    }
}
