package com.web.sms.repo;

import com.web.sms.entity.Flight;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFlightRepo extends CrudRepository<Flight, Integer>
{
    List<Flight> findByFlightNumber(String flightNumber);
}
