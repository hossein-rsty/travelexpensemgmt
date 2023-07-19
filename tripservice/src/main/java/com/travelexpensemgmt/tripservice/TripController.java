package com.travelexpensemgmt.tripservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @project: travelexpensemgmt
 * @author: Hossein Rostamiraeini
 */
@RestController
@Slf4j
@RequestMapping("/api/v1/trips")
public class TripController {
    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService)
    {
        this.tripService = tripService;
    }

    @GetMapping
    public ResponseEntity<List<Trip>> getTrips() {

        try {
            final List<Trip> trips = tripService.getTrips();
            return new ResponseEntity<>(trips, HttpStatus.OK);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping
    public ResponseEntity<Trip> saveTrip(@RequestBody Trip trip) {
        try {
            return new ResponseEntity<>(tripService.saveTrip(trip), HttpStatus.CREATED);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable("id") String id) {
        try {
            final Trip trip = tripService.getTripById(id);
            return new ResponseEntity<>(trip, HttpStatus.OK);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Methode delete a Trip by Id
    @DeleteMapping("/delete/{tripId}")
    public ResponseEntity<Trip> deleteTripkbyId(@PathVariable("tripId") String tripId) {
        try {
            Trip trip = tripService.deleteTripById(tripId);
            return new ResponseEntity<>(trip, HttpStatus.OK);
        }catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Method update the trips attributes
    @PutMapping
    public ResponseEntity<Trip> updateById(@RequestBody Trip trip) {
        try {
            Trip updatedTrip = tripService.updateById(trip);
            return new ResponseEntity<>(updatedTrip, HttpStatus.OK);
        }catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/assignuser/{id},{uId}")
    public ResponseEntity<Trip> setAssignedUser(@PathVariable("id") String id, @PathVariable("uId") String uId){
        try{
            final Trip trip = tripService.setAssignedUser(id, uId);
            return new ResponseEntity<>(trip, HttpStatus.OK);
        }catch(IllegalArgumentException iae){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/assignexpense/{id},{eId}")
    public ResponseEntity<Trip> setAssignedExpense(@PathVariable("id") String id, @PathVariable("eId") String eId){
        try{
            final Trip trip = tripService.setAssignedExpense(id, eId);
            return new ResponseEntity<>(trip, HttpStatus.OK);
        }catch(IllegalArgumentException iae){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
