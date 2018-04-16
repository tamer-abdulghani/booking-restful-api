/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Tamer
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CustomerRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

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

    }

}
