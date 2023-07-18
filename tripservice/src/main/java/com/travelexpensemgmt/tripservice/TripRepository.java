package com.travelexpensemgmt.tripservice;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @project: travelexpensemgmt
 * @author: Hossein Rostamiraeini
 */
public interface TripRepository extends MongoRepository<TripDbModel, String> {
}
