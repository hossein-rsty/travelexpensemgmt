package com.travelexpensemgmt.expenseservice;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @project: travelexpensemgmt
 * @author: Hossein Rostamiraeini
 */
public interface ExpenseRepository extends MongoRepository<ExpenseDbModel, String> {
}
