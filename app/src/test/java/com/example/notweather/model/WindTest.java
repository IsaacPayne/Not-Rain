package com.example.notweather.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class WindTest {

    @Test
    public void northDirectionIsCorrect() {
        assertEquals("N", new Wind(0, 337.5).getDirection());
        assertEquals("N", new Wind(0, 22.4).getDirection());
    }

    @Test
    public void northEastDirectionIsCorrect() {
        assertEquals("NE", new Wind(0, 22.5).getDirection());
        assertEquals("NE", new Wind(0, 67.4).getDirection());
    }

    @Test
    public void eastDirectionIsCorrect() {
        assertEquals("E", new Wind(0, 67.5).getDirection());
        assertEquals("E", new Wind(0, 112.4).getDirection());
    }

    @Test
    public void southEastDirectionIsCorrect() {
        assertEquals("SE", new Wind(0, 112.5).getDirection());
        assertEquals("SE", new Wind(0, 157.4).getDirection());
    }

    @Test
    public void southDirectionIsCorrect() {
        assertEquals("S", new Wind(0, 157.5).getDirection());
        assertEquals("S", new Wind(0, 202.4).getDirection());
    }

    @Test
    public void southWestDirectionIsCorrect() {
        assertEquals("SW", new Wind(0, 202.5).getDirection());
        assertEquals("SW", new Wind(0, 247.4).getDirection());
    }

    @Test
    public void westDirectionIsCorrect() {
        assertEquals("W", new Wind(0, 247.5).getDirection());
        assertEquals("W", new Wind(0, 292.4).getDirection());
    }

    @Test
    public void northWestDirectionIsCorrect() {
        assertEquals("NW", new Wind(0, 292.5).getDirection());
        assertEquals("NW", new Wind(0, 337.4).getDirection());
    }
}
