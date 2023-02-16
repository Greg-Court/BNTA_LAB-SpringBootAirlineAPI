package com.example.airline_api.controllers;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.services.FlightService;
import com.example.airline_api.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @Autowired
    PassengerService passengerService;

    // Display all available flights
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(@RequestParam Optional<String> destination) {
        if (destination.isPresent()) {
            List<Flight> flights = flightService.getAllFlightsContainingString(destination.get());
            if (flights.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(flights, HttpStatus.OK);
            }
        } else {
            List<Flight> flights = flightService.getAllFlights();
            if (flights.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(flights, HttpStatus.OK);
            }
        }
    }

    // Display a specific flight
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Flight>> getFlightById(@PathVariable Long id) {
        Optional<Flight> flight = flightService.getFlightById(id);
        if (flight.isPresent()) return new ResponseEntity<>(flight, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Add details of a new flight
    @PostMapping
    public ResponseEntity<Flight> addNewFlight(@RequestBody Flight flight){
        Flight newFlight = flightService.addFlight(flight);
        return new ResponseEntity<>(newFlight, HttpStatus.CREATED);
    }

    // Book passenger on a flight
    @PatchMapping(value = "/{flightId}/passengers/{passengerId}")
    public ResponseEntity<Flight> addPassengerToFlight(@PathVariable Long flightId,
                                                       @PathVariable Long passengerId) {
        Flight flight = flightService.getFlightById(flightId).orElse(null);
        Passenger passenger = passengerService.getPassengerById(passengerId).orElse(null);
        if (flight == null || passenger == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<Passenger> passengers = flight.getPassengers();
        if (passengers.size() < flight.getCapacity() && !passengers.contains(passenger)) {
            flightService.addPassengerToFlight(passenger, flight);
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Cancel flight
    @DeleteMapping("/{id}")
    public ResponseEntity<Flight> cancelFlight(@PathVariable Long id) {
        Optional<Flight> flight = flightService.getFlightById(id);
        if (flight.isPresent()) {
            flightService.cancelFlight(id);
            return new ResponseEntity(flight.get(), HttpStatus.OK); // this line was problematic before adding (fetch = FetchType.EAGER) to @ManyToMany in Flight.java
        } else return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

}
