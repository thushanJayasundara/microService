package org.airretailer.util;

import org.airretailer.dto.FlightDto;
import org.airretailer.dto.SearchResponseDto;

import java.util.Collections;
import java.util.Map;

public class ResponseUtil {

    private ResponseUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static CommonResponse mapSuccessCommonResponse(String responseMessage , Object payload, Map<String,Object> request){
        return CommonResponse.builder()
                .status(true)
                .messages(Collections.singletonList(responseMessage))
                .payload(Collections.singletonList(payload))
                .request(request)
                .build();
    }

    public static SearchResponseDto mapFlightDtoToSerSearchResponseDto(FlightDto flightDto){
        return SearchResponseDto.builder()
                .id(flightDto.getId())
                .flightNumber(flightDto.getFlightNumber())
                .airline(flightDto.getAirline())
                .seats(flightDto.getSeats())
                .cabinType(flightDto.getCabinType())
                .build();
    }

}
