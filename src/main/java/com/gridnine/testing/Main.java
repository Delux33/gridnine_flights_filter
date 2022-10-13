package com.gridnine.testing;


import com.gridnine.testing.factory.filter.FlightFilterFactory;
import com.gridnine.testing.factory.filter.flight_filter_imps.DepartureBeforeArrivalFlightFilter;
import com.gridnine.testing.factory.filter.flight_filter_imps.DepartureBeforeNowFlightFilter;
import com.gridnine.testing.factory.filter.flight_filter_imps.MoreThanTwoHoursOnGroundFlightFilter;
import com.gridnine.testing.factory.flight.FlightBuilder;
import com.gridnine.testing.factory.flight.bean.Flight;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilterFactory.doFilter(flights, new DepartureBeforeNowFlightFilter())
                .forEach(flight -> log.info(flight.toString()));

        FlightFilterFactory.doFilter(flights, new DepartureBeforeArrivalFlightFilter())
                .forEach(flight -> log.info(flight.toString()));

        FlightFilterFactory.doFilter(flights, new MoreThanTwoHoursOnGroundFlightFilter())
                .forEach(flight -> log.info(flight.toString()));
    }
}