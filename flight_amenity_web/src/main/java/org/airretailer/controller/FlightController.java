package org.airretailer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.airretailer.dto.FlightDto;
import org.airretailer.service.FlightService;
import org.airretailer.util.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/flight-management")
@Tag(name = "Flight APIs", description = "All CRUD APIs regarding Flight details")
public class FlightController {

private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @Operation(summary = "For use this API save flight details")
    @PostMapping("/")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<CommonResponse> createFlight(@RequestBody FlightDto flightDto){
        return new ResponseEntity<>(flightService.createFlight(flightDto), HttpStatus.OK);
    }

    @Operation(summary = "For use this API update flight details")
    @PutMapping("/")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<CommonResponse> updateFlight(@RequestBody FlightDto flightDto){
        return new ResponseEntity<>(flightService.updateFlight(flightDto), HttpStatus.OK);
    }

    @Operation(summary = "For use this API delete flight details by id")
    @DeleteMapping("/")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<CommonResponse> deleteFlight(@RequestParam("flightId") String flightId){
        return new ResponseEntity<>(flightService.deleteFlight(flightId), HttpStatus.OK);
    }

    @Operation(summary = "For use this API retrieve flight details by id ")
    @GetMapping("/flightId")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<CommonResponse> getFlightById(@RequestParam String flightId){
        return new ResponseEntity<>(flightService.getFlightById(flightId), HttpStatus.OK);
    }

    @Operation(summary = "For use this API retrieve flight details by name ")
    @GetMapping("/flightNumber")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<CommonResponse> getFlightByNumber(@RequestParam String flightNumber){
        return new ResponseEntity<>(flightService.getFlightByNumber(flightNumber), HttpStatus.OK);
    }

    @Operation(summary = "For use this API retrieve flight details by pageable ")
    @GetMapping("/")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<CommonResponse> getAllFlight(@RequestParam(required = false,defaultValue = "0") int page,
                                                       @RequestParam(required = false,defaultValue = "5") int size){
        return new ResponseEntity<>(flightService.getAllFlight(page , size), HttpStatus.OK);
    }

}
