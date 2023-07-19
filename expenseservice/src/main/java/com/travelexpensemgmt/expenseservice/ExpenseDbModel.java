package com.travelexpensemgmt.expenseservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Currency;

/**
 * @project: travelexpensemgmt
 * @author: Hossein Rostamiraeini
 */
@Document(collection = "Expense")
@AllArgsConstructor
@Builder
@Getter
public class ExpenseDbModel {
    @Id
    private String id;
    private Category category;
    private double amount;
    private Currency currency;
    private String description;
    private String assignedDocId;

}
