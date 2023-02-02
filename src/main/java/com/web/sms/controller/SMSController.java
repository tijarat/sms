package com.web.sms.controller;

import com.web.sms.dto.FlightDTO;
import com.web.sms.service.IFlightService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class SMSController 
{

    
    @Autowired
    IFlightService service;
    //Http Get Method - Read Operation
    @GetMapping
        (
            path = "/flight",
            name = "getAllFlights",
            produces = MediaType.APPLICATION_JSON_VALUE
            //consumes = MediaType.TEXT_PLAIN_VALUE//, 
            //headers = "content-type=text/plain"//"content-type=text/plain" //"content-type=text/json"
        )
    public List<FlightDTO> getAllFlights()
    {
        return service.getAllFlight();
    }
    
    @GetMapping
        (
            path = "/flight",
            name = "getFlightsByFlightNumber",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE//, 
            //headers = "content-type=text/plain"//"content-type=text/plain" //"content-type=text/json"
        )
    public List<FlightDTO> getFlightsByFlightNumber(@RequestBody String flightNumber)
    {
        return service.getFlightsByFlightNumber(flightNumber);
    }   
    
    @GetMapping
        (
            path = "/flight/data/{flightNumber}",
            name = "getFlightByFlightNumber",
            produces = MediaType.APPLICATION_JSON_VALUE
            //consumes = MediaType.APPLICATION_JSON_VALUE//, 
            //headers = "content-type=text/plain"//"content-type=text/plain" //"content-type=text/json"
        )
    public FlightDTO getFlightByFlightNumber(@PathVariable(name="flightNumber") String flightNumber)
    {
        return service.getFlightByFlightNumber(flightNumber);
    }     
    
    @GetMapping
        (
            path = "/flight/{id}",
            name = "getFlight",
            produces = MediaType.APPLICATION_JSON_VALUE
            //consumes = MediaType.TEXT_PLAIN_VALUE//, 
            //headers = "content-type=text/plain"//"content-type=text/plain" //"content-type=text/json"
        )
    public FlightDTO getFlight(@PathVariable(name="id") @Positive int flightId)
    {
        return service.getFlight(flightId);
    }    
    
    @PostMapping
        (
            path = "/flight",
            name = "createFlight",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = "content-type=text/json"
        )    
    public FlightDTO createFlight(@RequestBody @Valid FlightDTO flightDto)
    {
        return service.createFlight(flightDto);
    }
    
    @PutMapping
        (
            path = "/flight",
            name = "updateFlight",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = "content-type=text/json"
        )      
    public FlightDTO updateFlight(@RequestBody FlightDTO flightDto)
    {
        return service.updateFlight(flightDto);
    }
    
    @DeleteMapping
        (
            path = "/flight/{id}",
            name = "deleteFlight",
            produces = MediaType.APPLICATION_JSON_VALUE
            //consumes = MediaType.APPLICATION_JSON_VALUE,
           // headers = "content-type=text/json"
        )      
    public String deleteFlight(@PathVariable(name="id") int flightId)
    {
        service.deleteFlight(flightId);
        return "delete method invoked.";
    }
}
