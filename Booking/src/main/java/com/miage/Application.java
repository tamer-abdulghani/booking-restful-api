/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage;

import com.miage.repositories.TravellerRepository;
import com.miage.repositories.BookingRepository;
import com.miage.repositories.AirportRepository;
import com.miage.repositories.FlightRepository;
import java.time.LocalDateTime;
import java.time.Month;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User.UserBuilder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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
    }
    /*
    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @EnableWebSecurity
    static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Bean
        InMemoryUserDetailsManager userDetailsManager() {

            UserBuilder builder = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder();

            UserDetails admin = builder.username("admin").password("admin").roles("USER", "ADMIN").build();

            return new InMemoryUserDetailsManager(admin);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            
            http.httpBasic().and().authorizeRequests().
                    antMatchers(HttpMethod.POST, "/api/book/create").hasRole("ADMIN").
                    antMatchers(HttpMethod.PUT, "/api/book/update").hasRole("ADMIN").
                    antMatchers(HttpMethod.DELETE, "/api/book/cancel/**").hasRole("ADMIN").and().
                    csrf().disable();
             
        }
    }*/
}
