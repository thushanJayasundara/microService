/**
 * This class is responsible handle all logic's in AmenityService.
 * @author thushan vimukthi
 * @version 1.0
 * @since 08th of August 2022
 */
package org.airretailer.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.airretailer.constant.CommonMessage;
import org.airretailer.constant.enums.CabinType;
import org.airretailer.dto.FlightDto;
import org.airretailer.dto.SearchResponseDto;
import org.airretailer.exception.DataNotFoundException;
import org.airretailer.exception.NoSeatsFoundException;
import org.airretailer.service.AmenityService;
import org.airretailer.service.FlightService;
import org.airretailer.util.CommonResponse;

import org.airretailer.util.ResponseUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
public class AmenityServiceImpl implements AmenityService {

    private final FlightService flightService;
    private final RestTemplate restTemplate;


    @Value("${flight-amenity.amenity-url}")
    private String url;

    public AmenityServiceImpl(FlightService flightService, RestTemplateBuilder builder) {
        this.flightService = flightService;
        this.restTemplate = builder.build();
    }

    /**
     * This method is responsible retrieve the Amenity details by flight Id and cabin type=
     * @param flightNumber the flight number as we need to retrieve data
     * @param cabinType the cabin type as we need to retrieve data
     * @return CommonResponse
     */
    @Override
    public CommonResponse getAmenityByFlightNumberAndCabinType(String flightNumber, CabinType cabinType) {
        log.info(" Start calling method -> getAmenityByFlightNumberAndCabinType ");
        FlightDto flightDto =
                flightService.getFlightByFlightAndCabinType(flightNumber, cabinType);

        if (ObjectUtils.isNotEmpty(flightDto) && !flightDto.getSeats().equals("0")) {
            SearchResponseDto responseDto = ResponseUtil.mapFlightDtoToSerSearchResponseDto(flightDto);
            responseDto.setAmenities(getAmenityByCabinType(cabinType));
            Map<String,Object> request = Map.of("Flight Number",flightNumber,"Cabin Type",cabinType);
            return ResponseUtil.mapSuccessCommonResponse(CommonMessage.RETRIEVE_SUCCESS, responseDto,request);
        }else {
            throw new NoSeatsFoundException(String.format(CommonMessage.NO_SEATS_FOUND,flightNumber,cabinType));
        }
    }

    /**
     * This method is responsible retrieve the Amenity details by cabin type
     * @param cabinType the cabin type as we need to retrieve data
     * @return Map<String, String>
     */
    private Map<String, String> getAmenityByCabinType(CabinType cabinType) {
        log.info(" Start calling method -> getAmenityByCabinType | Rest call available here ");
        ArrayList<LinkedHashMap<String, LinkedHashMap<String, String>>> result =new ArrayList<>();
        if (ObjectUtils.isNotEmpty(url) && ObjectUtils.isNotEmpty(restTemplate)) {
                result = restTemplate.getForObject(url, ArrayList.class);
        }

        if (ObjectUtils.isNotEmpty(result) && result.isEmpty()) {
            log.info(" End calling method -> getAmenityByCabinType | Rest call end  ");
            return result.get(0).get(cabinType.label);
        }
         throw new DataNotFoundException(CommonMessage.DATA_NOT_FOUND);
    }
}
