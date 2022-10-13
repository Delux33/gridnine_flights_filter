package com.gridnine.testing.factory.filter.flight_filter_imps;

import com.gridnine.testing.factory.filter.FlightFilter;
import com.gridnine.testing.factory.flight.bean.Flight;
import com.gridnine.testing.factory.flight.bean.Segment;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Filter excludes flights, that have segments with an arrival date earlier than the departure date
 * @version 1.0.0
 */
@Slf4j
public class DepartureBeforeArrivalFlightFilter implements FlightFilter {
    /**
     * @param flights list of flights to filter
     * @return new list of flights after filtering
     */
    @Override
    public List<Flight> filter(final List<Flight> flights) {
        log.info("2. Filter excludes flights, that have segments with an arrival date earlier than the departure date");
        final List<Flight> resultFlightsFilter = new ArrayList<>();

        for (Flight flight : flights) {
            final boolean resultFilter = isNotFiltered(flight);
            if (resultFilter) resultFlightsFilter.add(flight);
        }
        return resultFlightsFilter;
    }

    private boolean isNotFiltered(final Flight flight) {
        for (Segment segment : flight.getSegments()) {
            if (segment.getDepartureDate().isAfter(segment.getArrivalDate())) return false;
        }
        return true;
    }
}
