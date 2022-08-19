/**
 * This class is responsible handle all logic's in PersonService.
 * @author thushan vimukthi
 * @version 1.0
 * @since 05th of August 2022
 */
package org.airretailer.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.airretailer.constant.CommonMessage;
import org.airretailer.constant.enums.CabinType;
import org.airretailer.constant.enums.CommonStatus;
import org.airretailer.dto.FlightDto;
import org.airretailer.entity.Flight;
import org.airretailer.exception.DataNotFoundException;
import org.airretailer.exception.DuplicationErrorException;
import org.airretailer.exception.MandatoryFieldException;
import org.airretailer.repository.FlightRepository;
import org.airretailer.service.FlightService;
import org.airretailer.util.CommonResponse;
import org.airretailer.util.FlightUtil;
import org.airretailer.util.ResponseUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /**
     * This method is responsible save the {@link Flight} details.
     * @param flightDto the flight detail as we need to save
     * @return CommonResponse
     */
    @Override
    public CommonResponse createFlight(FlightDto flightDto)  {
        log.info(" Start calling method -> createFlight ");
        String message = FlightUtil.validateFlightDto(flightDto);
        if(!ObjectUtils.isNotEmpty(message)){
            if (!flightRepository.existsByFlightNumber(flightDto.getFlightNumber())){
                Flight flight = FlightUtil.getFlightEntity(flightDto);
                flight = flightRepository.save(flight);
                Map<String,Object> request = Map.of("Flight Detail",flightDto);
                return ResponseUtil.mapSuccessCommonResponse(CommonMessage.CREATE_DATA,FlightUtil.getFlightDTO(flight),request);
            }else {
                throw new DuplicationErrorException(CommonMessage.FLIGHT_ALREADY_EXIST);
            }
        }else {
            throw new MandatoryFieldException(message);
        }

    }

    /**
     * This method is responsible update the {@link Flight} details.
     * @param flightDto the flight detail as we need to update
     * @return CommonResponse
     */
    @Override
    public CommonResponse updateFlight(FlightDto flightDto) {
        log.info(" Start calling method -> updateFlight ");
        String message = FlightUtil.validateFlightDto(flightDto);
        if(!ObjectUtils.isNotEmpty(message)){
            Flight newFlightDetails = FlightUtil.getFlightEntity(flightDto);
            Optional<Flight> oldFlight = flightRepository.findFlightByCommonStatusNotAndId
                    (CommonStatus.DELETE,newFlightDetails.getId());

            if (oldFlight.isPresent()){
                newFlightDetails = flightRepository.save(newFlightDetails);
                Map<String,Object> request = Map.of("Flight Detail",flightDto);
                return ResponseUtil.mapSuccessCommonResponse(CommonMessage.CREATE_UPDATE,
                        FlightUtil.getFlightDTO(newFlightDetails),request);
            }else {
                throw new DataNotFoundException(CommonMessage.FLIGHT_DETAIL_NOT_FOUND);
            }
        }
        else {
            throw new MandatoryFieldException(message);
        }
    }

    /**
     * This method is responsible delete the {@link Flight}  details by flight Id
     * @param flightId the flight id as we need to delete
     * @return CommonResponse
     */
    @Override
    public CommonResponse deleteFlight(String flightId) {
        log.info(" Start calling method -> deleteFlight ");
        if (StringUtils.isNotBlank(flightId)){
            Optional<Flight> retrievedFlight = flightRepository.findFlightByCommonStatusNotAndId
                    (CommonStatus.DELETE,flightId);
            if (retrievedFlight.isPresent()){
                Flight flight = retrievedFlight.get();
                flight.setCommonStatus(CommonStatus.DELETE);
                flightRepository.save(flight);
                Map<String,Object> request = Map.of("Flight ID",flightId);
                return ResponseUtil.mapSuccessCommonResponse(CommonMessage.CREATE_DELETE,null,request);
            }else {
                throw new DataNotFoundException(CommonMessage.FLIGHT_DETAIL_NOT_FOUND);
            }
        }else {
            throw new MandatoryFieldException(CommonMessage.ENTER_FLIGHT_NUMBER);
        }
    }

    /**
     * This method is responsible retrieve the {@link Flight}  details by flight Id
     * @param flightId the flight as we need to retrieve data
     * @return CommonResponse
     */
    @Override
    public CommonResponse getFlightById(String flightId) {
        log.info(" Start calling method -> getFlightById ");
        if (StringUtils.isNotBlank(flightId)){
            Optional<Flight> retrievedFlight = flightRepository.findFlightByCommonStatusNotAndId
                    (CommonStatus.DELETE,flightId);
            if (retrievedFlight.isPresent()){
                Map<String,Object> request = Map.of("Flight ID",flightId);
                return ResponseUtil.mapSuccessCommonResponse(CommonMessage.RETRIEVE_SUCCESS,
                        FlightUtil.getFlightDTO(retrievedFlight.get()),request);
            }else {
                throw new DataNotFoundException(CommonMessage.FLIGHT_DETAIL_NOT_FOUND);
            }
        }else {
            throw new MandatoryFieldException(CommonMessage.ENTER_FLIGHT_NUMBER);
        }
    }

    /**
     * This method is responsible retrieve the {@link Flight}  details by flight name
     * @param flightNumber the flight as we need to retrieve data
     * @return CommonResponse
     */
    @Override
    public CommonResponse getFlightByNumber(String flightNumber) {
        log.info(" Start calling method -> getFlightByName ");
        if (StringUtils.isNotBlank(flightNumber)){
            Flight retrievedFlight = flightRepository.findFlightByFlightNumber(flightNumber);
            if (ObjectUtils.isNotEmpty(retrievedFlight)){
                Map<String,Object> request = Map.of("Flight Number",flightNumber);
                log.info(" End calling method -> getFlightByName "+ request +" "+retrievedFlight.toString());
                return ResponseUtil.mapSuccessCommonResponse(CommonMessage.RETRIEVE_SUCCESS,
                        FlightUtil.getFlightDTO(retrievedFlight),request);
            }else {
                throw new DataNotFoundException(CommonMessage.FLIGHT_DETAIL_NOT_FOUND);
            }
        }else {
            throw new MandatoryFieldException(CommonMessage.ENTER_FLIGHT_NUMBER);
        }
    }

    /**
     * This method is responsible retrieve the {@link Flight}  details by pageable
     * @param page the page number as we need to retrieve data
     * @param size the page size as we need to retrieve data
     * @return CommonResponse
     */
    @Override
    public CommonResponse getAllFlight(int page, int size) {
        log.info(" Start calling method -> getAllFlight ");
        Pageable paging = PageRequest.of(page, size);
        Page<Flight> pagedFlights = flightRepository.findFlightByCommonStatusNot(CommonStatus.DELETE ,paging);
        Map<String,Object> request = Map.of("Page Number",page,"Page Size",size);
        log.info(" End calling method -> getAllFlight "+ request +" "+pagedFlights.toString());
        return ResponseUtil.mapSuccessCommonResponse(CommonMessage.RETRIEVE_SUCCESS,pagedFlights,request);
    }

    /**
     * This method is responsible retrieve the {@link FlightDto}  details by flight
     * number and cabin type (For internal use purpose only)
     * @param flightNumber the flight as we need to retrieve data
     * @param cabinType the cabin type as we need to retrieve data
     * @return FlightDto
     */
    @Override
    public FlightDto getFlightByFlightAndCabinType(String flightNumber, CabinType cabinType) {
        log.info(" Start calling method -> getFlightByFlightAndCabinType ");
        Flight flight = flightRepository.findOneByFlightNumberAndCabinType(flightNumber,cabinType);
        if (ObjectUtils.isNotEmpty(flight)){
            return FlightUtil.getFlightDTO(flight);
        }else {
            throw new DataNotFoundException(CommonMessage.FLIGHT_DETAIL_NOT_FOUND);
        }

    }

}
