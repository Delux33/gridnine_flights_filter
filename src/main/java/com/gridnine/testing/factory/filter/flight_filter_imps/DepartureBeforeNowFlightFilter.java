package com.gridnine.testing.factory.filter.flight_filter_imps;

import com.gridnine.testing.factory.filter.FlightFilter;
import com.gridnine.testing.factory.flight.bean.Flight;
import com.gridnine.testing.factory.flight.bean.Segment;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Filter excludes flights, departing before the current time
 * @version 1.0.0
 */
@Slf4j
public class DepartureBeforeNowFlightFilter implements FlightFilter {
    /**
     * @param flights list of flights to filter
     * @return new list of flights after filtering
     */
    @Override
    public List<Flight> filter(final List<Flight> flights) {
        log.info("1. Filter excludes flights, departing before the current time");
        final List<Flight> resultFlightsFilter = new ArrayList<>();

        for (Flight flight : flights) {
            final boolean resultFilter = isNotFiltered(flight);
            if (resultFilter) resultFlightsFilter.add(flight);
        }
        return resultFlightsFilter;
    }

    private boolean isNotFiltered(final Flight flight) {
        for (Segment segment : flight.getSegments()) {
            if (segment.getDepartureDate().isBefore(LocalDateTime.now())) return false;
        }
        return true;
    }
}
