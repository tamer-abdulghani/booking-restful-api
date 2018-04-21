/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage;

import com.miage.repositories.TravellerRepository;
import com.miage.repositories.BookingRepository;
import com.miage.models.Hotel;
import com.miage.models.Room;
import com.miage.models.RoomType;
import com.miage.models.roomBooked;
import com.miage.repositories.AirportRepository;
import com.miage.repositories.BookedRoomRepository;
import com.miage.repositories.FlightRepository;
import com.miage.repositories.HotelRepository;
import com.miage.repositories.RoomRepository;
import java.time.LocalDateTime;
import java.time.Month;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author Tamer
 */
@SpringBootApplication(scanBasePackages = {
    "com.miage.repositories", "com.miage.models", "com.miage", "com.miage.api",})
@ComponentScan({"com.miage.repositories", "com.miage"})
public class Application implements CommandLineRunner {

    @Autowired
    private BookingRepository repositoryBooking;

    @Autowired
    private TravellerRepository repositoryTraveller;

    @Autowired
    private FlightRepository repositoryFlight;

    @Autowired
    private HotelRepository repositoryHotel;

    @Autowired
    private BookedRoomRepository repositoryBookedRoom;

    @Autowired
    private RoomRepository repositoryRoom;

    @Autowired
    private AirportRepository airportsRepository;

    @Autowired
    private Initiation initation;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (repositoryFlight.count() < 100) {
            repositoryFlight.deleteAll();
            airportsRepository.deleteAll();
            repositoryBooking.deleteAll();
            repositoryTraveller.deleteAll();
            initation.createFlights();
        }

        repositoryHotel.deleteAll();
        repositoryBookedRoom.deleteAll();
        repositoryRoom.deleteAll();

        Hotel hot1 = new Hotel("town1 street1", "First", 12345, 2, "1@1.fr", "06");
        Hotel hot2 = new Hotel("town2 street2", "Second", 54321, 2, "2@2.fr", "07");
        hot1 = repositoryHotel.save(hot1);
        hot2 = repositoryHotel.save(hot2);

        Room room1 = new Room(RoomType.Double, 2, hot1);
        Room room2 = new Room(RoomType.Single, 1, hot1);
        Room room3 = new Room(RoomType.King, 5, hot2);
        room1 = repositoryRoom.save(room1);
        room2 = repositoryRoom.save(room2);
        room3 = repositoryRoom.save(room3);
        hot1.addRoom(room1);
        hot1.addRoom(room2);
        hot2.addRoom(room3);
        repositoryHotel.save(hot1);
        repositoryHotel.save(hot2);
        roomBooked rb1 = new roomBooked(LocalDateTime.of(2018, Month.MARCH, 1, 12, 0),
                LocalDateTime.of(2018, Month.MARCH, 2, 12, 0), room1);
        repositoryBookedRoom.save(rb1);
        //End Booking room part----
    }
}
