package com.travelexpensemgmt.tripservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @project: travelexpensemgmt
 * @author: Hossein Rostamiraeini
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Trip {
    private final String tripId;
    private String name;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private String purpose;
    private String assignedUserId;
    private String assignedExpenseId;
}
