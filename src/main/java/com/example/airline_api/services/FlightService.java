package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public List<Flight> getAllFlightsContainingString(String string) {
        return flightRepository.findByDestinationContainingIgnoreCase(string);
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public void cancelFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public void addPassengerToFlight(Passenger passenger, Flight flight) {
        flight.getPassengers().add(passenger);
        flightRepository.save(flight);
    }
}
