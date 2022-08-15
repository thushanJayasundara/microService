package org.airretailer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.airretailer.constant.enums.CabinType;
import org.airretailer.service.AmenityService;
import org.airretailer.util.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/amenity-management")
@Tag(name = "Amenity APIs", description = "Search API with Flight Amenity")
public class AmenityController {

    private final AmenityService amenityService;

    public AmenityController(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

    @Operation(summary = "For use this API retrieve Amenity details by flight number and cabin type ")
    @GetMapping("/")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<CommonResponse> getAmenityByFlightNumberAndCabinType(@RequestParam String flightNumber ,
                                                                               @RequestParam CabinType cabinType){
        return new ResponseEntity<>(amenityService.getAmenityByFlightNumberAndCabinType(flightNumber, cabinType), HttpStatus.OK);
    }
}
