package org.airretailer.util;

import org.airretailer.dto.FlightDto;
import org.airretailer.entity.Flight;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class FlightUtil {
    private FlightUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static FlightDto getFlightDTO (Flight flight) {
        return FlightDto.builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .airline(flight.getAirline())
                .seats(String.valueOf(flight.getSeats()))
                .cabinType(flight.getCabinType())
                .commonStatus(flight.getCommonStatus())
                .build();
    }

    public static Flight getFlightEntity (FlightDto flightDto) {
        Flight flightEntity = new Flight();

        if(ObjectUtils.isNotEmpty(flightDto.getId())) {
            flightEntity.setId(flightDto.getId());
        }

        if(StringUtils.isNotBlank(flightDto.getFlightNumber())) {
            flightEntity.setFlightNumber(flightDto.getFlightNumber());
        }

        if(StringUtils.isNotBlank(flightDto.getAirline())) {
            flightEntity.setAirline(flightDto.getAirline());
        }

        if(ObjectUtils.isNotEmpty(flightDto.getSeats())) {
            flightEntity.setSeats(Long.valueOf(flightDto.getSeats()));
        }

        if(ObjectUtils.isNotEmpty(flightDto.getCabinType())) {
            flightEntity.setCabinType(flightDto.getCabinType());
        }

        if(ObjectUtils.isNotEmpty(flightDto.getCommonStatus())) {
            flightEntity.setCommonStatus(flightDto.getCommonStatus());
        }
        return flightEntity;
    }

    public static String validateFlightDto(FlightDto flightDto){

        if(!StringUtils.isNotBlank(flightDto.getId())) {
            return "Please Enter Flight Number";
        }

        if(!StringUtils.isNotBlank(flightDto.getFlightNumber())) {
            return "Please Enter Flight Number";
        }

        if(!StringUtils.isNotBlank(flightDto.getAirline())) {
            return "Please Enter Airline";
        }

        if(!ObjectUtils.isNotEmpty(flightDto.getSeats())) {
           return "Please Enter Numbers of Seats";
        }

        if(!ObjectUtils.isNotEmpty(flightDto.getCabinType())) {
           return "Please Enter Cabin Type";
        }
        return null;
    }

}
