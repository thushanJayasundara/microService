package org.airretailer.dto;

import lombok.*;
import org.airretailer.constant.enums.CabinType;
import org.airretailer.constant.enums.CommonStatus;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

    private String id;
    private String flightNumber;
    private String airline;
    private String seats;
    private CabinType cabinType;
    private CommonStatus commonStatus;

}
