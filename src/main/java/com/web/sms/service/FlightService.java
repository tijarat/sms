package com.web.sms.service;

import com.web.sms.dto.FlightDTO;
import com.web.sms.entity.Flight;
import com.web.sms.repo.IFlightRepo;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService implements IFlightService
{
    @Autowired
    IFlightRepo flightRepo;
    
    @Override
    public FlightDTO createFlight(FlightDTO flightDto) 
    {
        Flight flight = new Flight();
        BeanUtils.copyProperties(flightDto, flight);
        flight = flightRepo.save(flight);
        BeanUtils.copyProperties(flightDto, flight,"mfdBy");
        return flightDto;
    }
    
    @Override
    public FlightDTO getFlight(int flightId)
    {
        Optional<Flight> flight = flightRepo.findById(flightId);
        FlightDTO flightDto = null;
        if(flight.isPresent())
        {
            flightDto = new FlightDTO();
            BeanUtils.copyProperties(flight.get(), flightDto);
        }
        return flightDto;
    }

    @Override
    public FlightDTO updateFlight(FlightDTO flightDto) 
    {
        Optional<Flight> flightOpt = flightRepo.findById(flightDto.getId());
        if(flightOpt.isPresent())
        {
           Flight flight = new Flight();
           BeanUtils.copyProperties(flightDto, flight);
           flight = flightRepo.save(flight);
           BeanUtils.copyProperties(flight, flightDto);
        }else
        {
           //TODO
        }
        return flightDto;
    }

    @Override
    public void deleteFlight(int flightId) 
    {
        Optional<Flight> flightOpt = flightRepo.findById(flightId);
        if(flightOpt.isPresent())
        {
           Flight flight = new Flight();
           flightRepo.delete(flightOpt.get());
        }else
        {
           //TODO
        }
    }

    @Override
    public List<FlightDTO> getAllFlight() 
    {
        Iterable<Flight> flights = flightRepo.findAll();
        List<FlightDTO> flightsList = StreamSupport.stream(flights.spliterator(), false).map(flight -> {
            FlightDTO dto = new FlightDTO();
            BeanUtils.copyProperties(flight, dto);
            return dto;
        }).collect(Collectors.toList());
        flightsList.forEach(flightDto -> System.out.println("Printing List<FlightDTO>::"+flightsList));
        
        List<Flight> flightList = StreamSupport.stream(flights.spliterator(), false).toList();
        flightList.forEach(flight -> System.out.println("Printing List<Flight>flightList::"+flightList));
        List<Flight> flightList1 = StreamSupport.stream(flights.spliterator(), false).collect(Collectors.toList());
        flightList1.forEach(flight -> System.out.println("Printing List<Flight>flightList1::"+flightList1));
        return flightsList;
    }

    @Override
    public List<FlightDTO> getFlightsByFlightNumber(String flightNumber) 
    {
        Iterable<Flight> flights = flightRepo.findByFlightNumber(flightNumber);
        List<FlightDTO> flightsList = StreamSupport.stream(flights.spliterator(), false).map(flight -> {
            FlightDTO dto = new FlightDTO();
            BeanUtils.copyProperties(flight, dto);
            return dto;
        }).collect(Collectors.toList());  
        return flightsList;
    }

    @Override
    public FlightDTO getFlightByFlightNumber(String flightNumber) 
    {
        Iterable<Flight> flightOpt = flightRepo.findByFlightNumber(flightNumber);
        Optional<Flight> flight = StreamSupport.stream(flightOpt.spliterator(), false).findAny();
        FlightDTO flightDto= null;
        if(flight.isPresent())
        {
           flightDto = new FlightDTO();
           BeanUtils.copyProperties(flight.get(), flightDto);
        }else
        {
           //TODO
        }
        return flightDto;
    }
}
