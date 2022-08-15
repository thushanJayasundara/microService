package org.airretailer.service;

import org.airretailer.constant.enums.CabinType;
import org.airretailer.util.CommonResponse;

public interface AmenityService {

    CommonResponse getAmenityByFlightNumberAndCabinType (String flightNumber, CabinType cabinType);

}