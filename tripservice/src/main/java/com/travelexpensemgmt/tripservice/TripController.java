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
    public ResponseEntity<Trip> deleteTripkbyId(@PathVariable("tripId") String taskId) {
        try {
            Trip task = tripService.deleteTripById(taskId);
            return new ResponseEntity<>(task, HttpStatus.OK);
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
}
