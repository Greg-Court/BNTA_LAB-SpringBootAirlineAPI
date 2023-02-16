package com.example.airline_api.components;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    public DataLoader() {}

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Flight flight1 = new Flight("Paris", 120, LocalDate.of(2023, 3, 5), LocalTime.of(9, 0));
        Flight flight2 = new Flight("Amsterdam", 100, LocalDate.of(2023, 3, 7), LocalTime.of(10, 30));
        Flight flight3 = new Flight("Barcelona", 80, LocalDate.of(2023, 3, 10), LocalTime.of(14, 15));
        Flight flight4 = new Flight("Dublin", 90, LocalDate.of(2023, 3, 11), LocalTime.of(11, 45));
        Flight flight5 = new Flight("Berlin", 110, LocalDate.of(2023, 3, 12), LocalTime.of(16, 20));
        Flight flight6 = new Flight("Rome", 70, LocalDate.of(2023, 3, 15), LocalTime.of(8, 45));
        Flight flight7 = new Flight("Prague", 85, LocalDate.of(2023, 3, 17), LocalTime.of(13, 0));
        Flight flight8 = new Flight("Vienna", 95, LocalDate.of(2023, 3, 20), LocalTime.of(10, 30));
        Flight flight9 = new Flight("Brussels", 75, LocalDate.of(2023, 3, 22), LocalTime.of(15, 45));
        Flight flight10 = new Flight("Munich", 80, LocalDate.of(2023, 3, 25), LocalTime.of(12, 0));

        Passenger passenger1 = new Passenger("Colin", "colin@gmail.com");
        Passenger passenger2 = new Passenger("Ed", "ed@gmail.com");
        Passenger passenger3 = new Passenger("Joe", "joe@gmail.com");
        Passenger passenger4 = new Passenger("Richard", "richard@gmail.com");
        Passenger passenger5 = new Passenger("Greg", "greg@gmail.com");
        Passenger passenger6 = new Passenger("Chinika", "chinika@gmail.com");
        Passenger passenger7 = new Passenger("Samra", "samra@gmail.com");
        Passenger passenger8 = new Passenger("James", "james@gmail.com");
        Passenger passenger9 = new Passenger("Aya", "aya@gmail.com");
        Passenger passenger10 = new Passenger("Zsolt", "zsolt@gmail.com");

        flight1.getPassengers().add(passenger1);
        flight1.getPassengers().add(passenger2);
        flight1.getPassengers().add(passenger3);
        flight1.getPassengers().add(passenger4);
        flight1.getPassengers().add(passenger5);

        flight2.getPassengers().add(passenger6);
        flight2.getPassengers().add(passenger7);
        flight2.getPassengers().add(passenger8);
        flight2.getPassengers().add(passenger9);
        flight2.getPassengers().add(passenger10);

        passengerRepository.save(passenger1);
        passengerRepository.save(passenger2);
        passengerRepository.save(passenger3);
        passengerRepository.save(passenger4);
        passengerRepository.save(passenger5);
        passengerRepository.save(passenger6);
        passengerRepository.save(passenger7);
        passengerRepository.save(passenger8);
        passengerRepository.save(passenger9);
        passengerRepository.save(passenger10);

        flightRepository.save(flight1);
        flightRepository.save(flight2);
        flightRepository.save(flight3);
        flightRepository.save(flight4);
        flightRepository.save(flight5);
        flightRepository.save(flight6);
        flightRepository.save(flight7);
        flightRepository.save(flight8);
        flightRepository.save(flight9);
        flightRepository.save(flight10);

    }
}
