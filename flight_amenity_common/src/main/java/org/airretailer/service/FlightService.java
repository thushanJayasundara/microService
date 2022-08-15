package org.airretailer.service;

import org.airretailer.constant.enums.CabinType;
import org.airretailer.dto.FlightDto;
import org.airretailer.util.CommonResponse;

public interface FlightService {

    CommonResponse createFlight(FlightDto flightDto);

    CommonResponse updateFlight(FlightDto flightDto);

    CommonResponse deleteFlight(String flightId);

    CommonResponse getFlightById(String flightId);

    CommonResponse getFlightByNumber(String flightName);

    CommonResponse getAllFlight(int page, int size);

    FlightDto getFlightByFlightAndCabinType(String flightNumber, CabinType cabinType);

}
