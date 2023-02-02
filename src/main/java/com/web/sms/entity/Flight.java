package com.web.sms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import java.util.Calendar;


@Entity(name="flight")
public class Flight 
{
    @Id
    @Column(name="flight_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name="flight_number", nullable=false)
    private String flightNumber;
    @Column(name="capacity", nullable=false)
    private int capacity;
    @Column(name="mfd_by", nullable=false)
    private String mfdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="mfd_on", nullable=false)
    private Calendar mfdOn;
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
  
    public String getMfdBy() {
        return mfdBy;
    }

    
    public void setMfdBy(String mfdBy) {
        this.mfdBy = mfdBy;
    }

    public Calendar getMfdOn() {
        return mfdOn;
    }

    public void setMfdOn(Calendar mfdOn) {
        this.mfdOn = mfdOn;
    }

    @Override
    public String toString() {
        return "Flight{" + "id=" + id + ", flightNumber=" + flightNumber + ", capacity=" + capacity + ", mfdBy=" + mfdBy + ", mfdOn=" + mfdOn + '}';
    }
    
    
    
}
