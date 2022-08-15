package org.airretailer.dto;

import lombok.Builder;
import lombok.Data;
import org.airretailer.constant.enums.CabinType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Map;
@Data
@Builder
public class SearchResponseDto {

    private String id;
    private String flightNumber;
    private String airline;
    private String seats;
    @Enumerated(EnumType.STRING)
    private CabinType cabinType;
    private Map<String, String> amenities;

}
