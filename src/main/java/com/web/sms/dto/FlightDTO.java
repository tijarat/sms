package com.web.sms.dto;

import com.web.sms.validation.FlightMfdBy;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Calendar;
import java.util.Objects;

public class FlightDTO 
{
    private int id;
    @NotNull
    private String flightNumber;
    @NotNull
    @PositiveOrZero
    private int capacity;
    @NotNull
    @FlightMfdBy
    private String mfdBy;
    @NotNull
    @Past
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
        return "FlightDTO{" + "id=" + id + ", flightNumber=" + flightNumber + ", capacity=" + capacity + ", mfdBy=" + mfdBy + ", mfdOn=" + mfdOn + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.flightNumber);
        hash = 79 * hash + Objects.hashCode(this.mfdBy);
        hash = 79 * hash + Objects.hashCode(this.mfdOn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FlightDTO other = (FlightDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.capacity != other.capacity) {
            return false;
        }
        if (!Objects.equals(this.flightNumber, other.flightNumber)) {
            return false;
        }
        if (!Objects.equals(this.mfdBy, other.mfdBy)) {
            return false;
        }
        return Objects.equals(this.mfdOn, other.mfdOn);
    }
    
}
