package com.gridnine.testing;

import com.gridnine.testing.factory.filter.FlightFilterFactory;
import com.gridnine.testing.factory.filter.flight_filter_imps.DepartureBeforeArrivalFlightFilter;
import com.gridnine.testing.factory.filter.flight_filter_imps.DepartureBeforeNowFlightFilter;
import com.gridnine.testing.factory.filter.flight_filter_imps.MoreThanTwoHoursOnGroundFlightFilter;
import com.gridnine.testing.factory.flight.FlightBuilder;
import com.gridnine.testing.factory.flight.bean.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class MainTest {
    final List<Flight> flights = FlightBuilder.createFlights();

    @Test
    void checkSizeListDepartureBeforeArrivalFlightFilter() {
        List<Flight> resultFilter = FlightFilterFactory.doFilter(flights, new DepartureBeforeArrivalFlightFilter());
        Assertions.assertEquals(flights.size() - 1, resultFilter.size());
    }

    @Test
    void checkRemoveElementAfterDepartureBeforeArrivalFlightFilter() {
        List<Flight> resultFilter = FlightFilterFactory.doFilter(flights, new DepartureBeforeArrivalFlightFilter());
        Assertions.assertEquals(getCopyFlightsWithoutOneElement(3), resultFilter);
    }

    @Test
    void checkSizeListDepartureBeforeNowFlightFilter() {
        List<Flight> resultFilter = FlightFilterFactory.doFilter(flights, new DepartureBeforeNowFlightFilter());
        Assertions.assertEquals(flights.size() - 1, resultFilter.size());
    }

    @Test
    void checkRemoveElementAfterDepartureBeforeNowFlightFilter() {
        List<Flight> resultFilter = FlightFilterFactory.doFilter(flights, new DepartureBeforeNowFlightFilter());
        Assertions.assertEquals(getCopyFlightsWithoutOneElement(2), resultFilter);
    }

    @Test
    void checkSizeListMoreThanTwoHoursOnGroundFlightFilter() {
        List<Flight> resultFilter = FlightFilterFactory.doFilter(flights, new MoreThanTwoHoursOnGroundFlightFilter());
        Assertions.assertEquals(flights.size() - 2, resultFilter.size());
    }

    @Test
    void checkRemoveElementAfterMoreThanTwoHoursOnGroundFlightFilter() {
        List<Flight> resultFilter = FlightFilterFactory.doFilter(flights, new MoreThanTwoHoursOnGroundFlightFilter());
        Assertions.assertEquals(getCopyFlightsWithoutOneElement(5, 4), resultFilter);
    }

    List<Flight> getCopyFlightsWithoutOneElement(int... index) {
        ArrayList<Flight> copyFlightsList = new ArrayList<>(flights);
        for (int i : index) {
            copyFlightsList.remove(i);
        }
        return copyFlightsList;
    }
}