package com.gridnine.testing.factory.filter.flight_filter_imps;

import com.gridnine.testing.factory.filter.FlightFilter;
import com.gridnine.testing.factory.flight.bean.Flight;
import com.gridnine.testing.factory.flight.bean.Segment;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Filter excludes flights, whose total time spent on the ground exceeds two hours
 * @version 1.0.0
 */
@Slf4j
public class MoreThanTwoHoursOnGroundFlightFilter implements FlightFilter {
    /**
     * @param flights - list of flights to filter
     * @return new list of flights after filtering
     */
    @Override
    public List<Flight> filter(final List<Flight> flights) {
        log.info("3. Filter excludes flights, whose total time spent on the ground exceeds two hours");
        final List<Flight> resultFlightsFilter = new ArrayList<>();

        for (Flight flight : flights) {
            final List<Segment> segments = flight.getSegments();

            if (segments.size() > 1) {
                final boolean resultFilter = isLessThanTwoHours(segments);

                if (resultFilter) resultFlightsFilter.add(flight);
            } else resultFlightsFilter.add(flight);
        }
        return resultFlightsFilter;
    }

    private boolean isLessThanTwoHours(final List<Segment> segments) {
        long timeOnGround = 0;
        for (int index = 0; index < segments.size() - 1; index++) {
            final LocalDateTime arrivalDate = segments.get(index).getArrivalDate();
            final LocalDateTime departureDateNextSegment = segments.get(index + 1).getDepartureDate();
            timeOnGround += arrivalDate.until(departureDateNextSegment, ChronoUnit.MINUTES);
        }
        return (timeOnGround / 60) < 2;
    }
}
