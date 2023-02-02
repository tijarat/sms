package com.web.sms.service;

import com.web.sms.dto.FlightDTO;
import java.util.List;

public interface IFlightService 
{
    public FlightDTO createFlight(FlightDTO flightDto);
    public FlightDTO getFlight(int flightId);

    public FlightDTO updateFlight(FlightDTO flightDto);

    public void deleteFlight(int flightId);

    public List<FlightDTO> getAllFlight();

    public List<FlightDTO> getFlightsByFlightNumber(String flightNumber);

    public FlightDTO getFlightByFlightNumber(String flightNumber);
}
