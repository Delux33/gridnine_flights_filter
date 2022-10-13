package com.gridnine.testing.factory.filter;

import com.gridnine.testing.factory.flight.bean.Flight;

import java.util.List;

public interface FlightFilter {
    List<Flight> filter(List<Flight> flights);
}
