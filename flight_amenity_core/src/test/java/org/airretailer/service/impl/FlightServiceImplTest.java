package org.airretailer.service.impl;

import org.airretailer.constant.enums.CabinType;
import org.airretailer.constant.enums.CommonStatus;
import org.airretailer.dto.FlightDto;
import org.airretailer.entity.Flight;
import org.airretailer.repository.FlightRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;
    private FlightServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new FlightServiceImpl(flightRepository);
    }

    @Test
    void canGetFlightById() {
        String flightId="FA002";
        Flight flight=Flight.builder()
            .id(flightId)
            .build();
        //When
        when(flightRepository.findFlightByCommonStatusNotAndId(any(),any())).thenReturn(Optional.of(flight));
       //then
        underTest.getFlightById("FA002");
        verify(flightRepository).findFlightByCommonStatusNotAndId(CommonStatus.DELETE,flightId);
    }

    @Test
    void canGetFlightByNumber() {
        String flightNumber="FL01";
        Flight flight=Flight.builder()
            .flightNumber(flightNumber)
            .build();
        //When
        when(flightRepository.findFlightByFlightNumber(any())).thenReturn(flight);
        //then
        underTest.getFlightByNumber(flightNumber);
        verify(flightRepository).findFlightByFlightNumber(flightNumber);
    }

    @Test
    void canCreateFlight() {
        //given
        FlightDto flightDto = FlightDto.builder()
            .id("FA020")
            .flightNumber("FL20")
            .airline("BA")
            .cabinType(CabinType.BUSINESS)
            .commonStatus(CommonStatus.ACTIVE)
            .seats("21")
            .build();

        //return
        Flight flight = Flight.builder()
            .id("FA020")
            .flightNumber("FL20")
            .airline("BA")
            .cabinType(CabinType.BUSINESS)
            .commonStatus(CommonStatus.ACTIVE)
            .seats(21L)
            .build();

        //when
        when(flightRepository.save(any())).thenReturn(flight);

        underTest.createFlight(flightDto);

        //then
        ArgumentCaptor<Flight> flightArgumentCaptor =
            ArgumentCaptor.forClass(Flight.class);

        verify(flightRepository).save(flightArgumentCaptor.capture());

        Flight flightArgumentCaptorValue = flightArgumentCaptor.getValue();

        assertThat(flightArgumentCaptorValue).isEqualTo(flight);
    }

}