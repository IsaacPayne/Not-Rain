package com.example.notweather.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MainTest {

    @Test
    public void formattedTemperatureIsCorrect() {
        Main main = new Main(19.678, 0.0, 0, 0.0, 0.0, 0.0, 0.0, 0.0);
        assertEquals("19.7 °C", main.getFormattedTemperature());
    }
}
