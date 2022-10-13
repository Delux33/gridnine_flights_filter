package com.gridnine.testing.factory.filter;

import com.gridnine.testing.factory.flight.bean.Flight;

import java.util.List;

/**
 * Factory class to select required filter
 */
public class FlightFilterFactory {
    private FlightFilterFactory() {
    }
    public static List<Flight> doFilter(final List<Flight> flights, final FlightFilter flightFilter) {
        return flightFilter.filter(flights);
    }
}
