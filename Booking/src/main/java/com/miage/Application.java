/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage;

import com.miage.repositories.CustomerRepository;
import com.miage.repositories.TravellerRepository;
import com.miage.repositories.BookingRepository;
import com.miage.models.Booking;
import com.miage.models.Customer;
import com.miage.models.Flight;
import com.miage.models.Traveller;
import com.miage.repositories.FlightRepository;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author Tamer
 */
@SpringBootApplication(scanBasePackages = {
    "com.miage.repositories", "com.miage.models"})
@ComponentScan({"com.miage.repositories"})
public class Application implements CommandLineRunner {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private BookingRepository repositoryBooking;

    @Autowired
    private TravellerRepository repositoryTraveller;

    @Autowired
    private FlightRepository repositoryFlight;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();
        repositoryFlight.deleteAll();
        repositoryTraveller.deleteAll();
        repositoryBooking.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith", "1245"));
        repository.save(new Customer("Bob", "Smith", "1246"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }

        // fetch an individual customer
        System.out.println("Customer found with findByPhoneNumber('1245'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByPhoneNumber("1245"));

        // fetch an individual customer
        System.out.println("Customer found with findByPhoneFirst('Alice',1245'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByPhoneAndFirstName("Alice", "1245"));

        //SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        //Date d = format.parse("10/05/2015");
        LocalDateTime a = LocalDateTime.of(2015, Month.FEBRUARY, 20, 20, 06);
        LocalDateTime b = LocalDateTime.of(2015, Month.FEBRUARY, 20, 20, 06);
        LocalDate vc = LocalDate.of(1990, Month.MAY, 20);
        Flight f = new Flight("TLS", "AMS", a, b, "AR350", 180, "FRA");
        Flight f2 = new Flight("TLS2", "AMS", a, b, "AR350", 180, "FRA");
        f = repositoryFlight.save(f);
        f2 = repositoryFlight.save(f2);

        Traveller t = new Traveller("Tamer", "AS", "ASD", "DCCSA", vc, "#.com");
        Traveller t2 = new Traveller("Tamer2", "AS2", "ASD", "DCCSA", vc, "#.com");
        Booking b1 = new Booking();
        List<Flight> li = new ArrayList<Flight>();
        li.add(f);
        li.add(f2);

        List<Traveller> liT = new ArrayList<Traveller>();
        t = repositoryTraveller.save(t);
        t2 = repositoryTraveller.save(t2);
        liT.add(t);
        liT.add(t2);

        b1.setProducts(li);
        b1.setTravellers(liT);

        repositoryBooking.save(b1);

    }

}
