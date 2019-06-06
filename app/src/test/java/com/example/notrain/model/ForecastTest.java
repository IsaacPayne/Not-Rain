package com.example.notrain.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ForecastTest {
    @Mock Wind wind;
    @Mock Weather weather;
    @Mock Main main;
    @Mock Clouds clouds;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void dateStringIsCorrect() {
        Long tueOnePm = 1560178800000L;
        Forecast forecast = new Forecast(0, 1, wind, weather, main, clouds, tueOnePm, "");

        assertEquals("Tuesday 01:00 PM", forecast.getDateAsLocalTime());
    }
}
