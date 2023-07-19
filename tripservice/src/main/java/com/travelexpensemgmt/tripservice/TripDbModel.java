package com.travelexpensemgmt.tripservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * @project: travelexpensemgmt
 * @author: Hossein Rostamiraeini
 */
@Document(collection = "Trip")
@AllArgsConstructor
@Builder
@Getter
public class TripDbModel {
    @Id
    private String id;
    private String name;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private String purpose;
    private String assignedUserId;
    private String assignedExpenseId;
}
