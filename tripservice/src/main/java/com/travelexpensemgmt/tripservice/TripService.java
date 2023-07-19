package com.travelexpensemgmt.tripservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @project: travelexpensemgmt
 * @author: Hossein Rostamiraeini
 */
@Service
public class TripService {
    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    /**
     *
     * @param trip Object to be saved in DB
     * @return the saved Object
     */
    public Trip saveTrip(Trip trip){
        final TripDbModel savedTrip = tripRepository.save(createTripDbModel(trip));
        return createTrip(savedTrip);
    }

    /**
     *
     * @return a List of all Trip Objects existing in DB
     */
    public List<Trip> getTrips(){
        List<TripDbModel> trips = tripRepository.findAll();
        return trips
                .stream()
                .map(this::createTrip)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param tripId The id of the trip to be returned
     * @return A Trip with the given ID
     */
    public Trip getTripById(String tripId) {
        final TripDbModel tripDbModel = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found by id: " + tripId));
        return createTrip(tripDbModel);
    }
    /**
     * Delete a Trip by Id
     *
     * @param tripId the unique Id of a Trip which is requiered for delete
     * @return the deleted Trip
     */
    public Trip deleteTripById(String tripId){
        try {
            final Trip trip = getTripById(tripId);
            tripRepository.deleteById(tripId);
            return trip;
        } catch (Exception e) {
            throw new IllegalArgumentException("Delete failed with error: " + e.getMessage());
        }
    }

    /**
     *
     * @param trip existing trip object to be edited
     * @return the updated Trip Object
     */
    public Trip updateById(Trip trip){
        if(tripRepository.existsById(trip.getTripId())){
            final TripDbModel updatedTrip = tripRepository.save(editTripDbModel(trip));
            return createTrip(updatedTrip);
        }else{
            throw new IllegalArgumentException("Trip not found by id: " + trip.getTripId());
        }
    }
    /**
     *
     * @param tripId the ID of the trip to be assigned to a user
     * @param userId the ID of the user, which will get a Trip assigned to him
     * @return the assigned Trip
     */
    public Trip setAssignedUser(String tripId, String userId){
        Trip trip = getTripById(tripId);
        trip.setAssignedUserId(userId);
        final TripDbModel savedTrip = tripRepository
                .save(editTripDbModel(trip));
        return createTrip(savedTrip);
    }
    /**
     *
     * @param tripId the ID of the trip to be assigned to an Expense
     * @param expenseId the ID of the expense, which will get a Trip assigned to it
     * @return the assigned Trip
     */
    public Trip setAssignedExpense(String tripId, String expenseId){
        Trip trip = getTripById(tripId);
        trip.setAssignedExpenseId(expenseId);
        final TripDbModel savedTrip = tripRepository
                .save(editTripDbModel(trip));
        return createTrip(savedTrip);
    }
    /**
     * INTERNAL
     * @param trip to be converted to a DB-Model Object
     * @return the converted object
     */
    private TripDbModel createTripDbModel(Trip trip) {
        return TripDbModel.builder()
                .id(null)
                .name(trip.getName())
                .destination(trip.getDestination())
                .startDate(trip.getStartDate())
                .endDate(trip.getEndDate())
                .purpose(trip.getPurpose())
                .assignedUserId(trip.getAssignedUserId())
                .assignedExpenseId(trip.getAssignedExpenseId())
                .build();
    }

    /**
     * INTERNAL
     * @param trip to be converted to a DB-Model Object for editing purposes (The ID will be given too)
     * @return the converted object
     */
    private TripDbModel editTripDbModel(Trip trip) {
        return TripDbModel.builder()
                .id(trip.getTripId())
                .name(trip.getName())
                .destination(trip.getDestination())
                .startDate(trip.getStartDate())
                .endDate(trip.getEndDate())
                .purpose(trip.getPurpose())
                .assignedUserId(trip.getAssignedUserId())
                .assignedExpenseId(trip.getAssignedExpenseId())
                .build();
    }

    /**
     * INTERNAL
     * @param tripDbModel to be converted to a Trip Object
     * @return the converted object
     */
    private Trip createTrip(TripDbModel tripDbModel) {
        return Trip.builder()
                .tripId(tripDbModel.getId())
                .name(tripDbModel.getName())
                .destination(tripDbModel.getDestination())
                .startDate(tripDbModel.getStartDate())
                .endDate(tripDbModel.getEndDate())
                .purpose(tripDbModel.getPurpose())
                .assignedUserId(tripDbModel.getAssignedUserId())
                .assignedExpenseId(tripDbModel.getAssignedExpenseId())
                .build();
    }
}
