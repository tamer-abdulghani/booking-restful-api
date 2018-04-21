/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.api;

import com.miage.models.Booking;
import com.miage.models.PaymentAccess;
import com.miage.models.Traveller;
import com.miage.models.exception.BookingContainsNoProducts;
import com.miage.models.exception.BookingNotFoundException;
import com.miage.models.exception.ExceptionInfo;
import com.miage.repositories.BookingRepository;
import com.miage.repositories.TravellerRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Tamer
 */
@RestController
@RequestMapping("/api/book")
public class BookingAPI {

    private BookingRepository bookingRepository;
    private TravellerRepository travellerRepository;
    RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private Environment env;

    @Autowired
    public BookingAPI(BookingRepository bookingRepository, TravellerRepository travellerRepository) {
        this.bookingRepository = bookingRepository;
        this.travellerRepository = travellerRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Booking getBookingById(@PathVariable("id") String id) {
        if (id != null && !"".equals(id)) {
            Optional<Booking> booking = bookingRepository.findById(id);
            if (booking.isPresent()) {
                return booking.get();
            }
        }
        throw new BookingNotFoundException(1, "Booking not found");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public List<Booking> listBookingsBetweenTwoDates(
            @RequestParam(value = "startDate", required = true) Date startDate,
            @RequestParam(value = "endDate", required = true) Date endDate) {
        System.out.println("" + startDate);
        System.out.println("" + endDate);
        return bookingRepository.find(startDate, endDate);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Booking book(@RequestBody
            @Valid Booking booking
    ) {
        if (booking.getTravellers().size() == 0 || booking.getFlights().size() == 0) {
            throw new BookingContainsNoProducts(2, "Booking contains no flights or travellers");
        }

        for (Traveller t : booking.getTravellers()) {
            t = travellerRepository.save(t);
        }

        return bookingRepository.save(booking);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/cancel/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable String id) {
        bookingRepository.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/payment/accesskey")
    public String paymentAccessKey(@RequestBody @Valid Booking booking) {
        PaymentAccess accessObject = new PaymentAccess();
        accessObject.setBookingId(booking.getId());
        accessObject.setfName(booking.getTravellers().get(0).getFirstName());
        accessObject.setlName(booking.getTravellers().get(0).getLastName());
        accessObject.setEmail(booking.getTravellers().get(0).getEmail());
        accessObject.setPhone(booking.getTravellers().get(0).getPhoneNumber());
        accessObject.setAmount(booking.getTotalAmount());

        HttpEntity<PaymentAccess> request = new HttpEntity<PaymentAccess>(accessObject);

        String host = env.getProperty("payment.api.host");
        String port = env.getProperty("payment.api.port");
        String myApiKey = env.getProperty("payment.api.key");

        String accessKey = restTemplate.postForObject("http://" + host + ":" + port + "/accounts/" + myApiKey + "/paymentAccessKey", request, String.class);

        return accessKey;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));
    }

    @ExceptionHandler(BookingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ExceptionInfo handleBadRequest(HttpServletRequest req, Exception ex) {
        return new ExceptionInfo(1, "Booking not found", "url");
    }
}
