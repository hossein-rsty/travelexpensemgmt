package com.travelexpensemgmt.expenseservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Currency;

/**
 * @project: travelexpensemgmt
 * @author: Hossein Rostamiraeini
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Expense {
    private final String expenseId;
    private Category category;
    private double amount;
    private Currency currency;
    private String description;

}
