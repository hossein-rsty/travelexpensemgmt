package com.travelexpensemgmt.docservice;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @project: travelexpensemgmt
 * @author: Hossein Rostamiraeini
 */
public interface DocRepository extends MongoRepository<DocDbModel, String> {
}
