package org.airretailer.repository;


import org.airretailer.constant.enums.CabinType;
import org.airretailer.constant.enums.CommonStatus;
import org.airretailer.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    boolean existsByFlightNumber(String flightNumber);

    Flight findFlightByFlightNumber(String flightNumber);

    Optional<Flight> findFlightByCommonStatusNotAndId(CommonStatus commonStatus, String id);

    Page<Flight> findFlightByCommonStatusNot(CommonStatus commonStatus, Pageable page);

    Flight findOneByFlightNumberAndCabinType(String flightNumber, CabinType cabinType);

}
