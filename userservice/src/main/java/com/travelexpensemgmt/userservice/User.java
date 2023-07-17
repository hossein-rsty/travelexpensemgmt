package com.travelexpensemgmt.userservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    private final String userId;
    private String firstName;
    private String lastName;
    private String email;
    private boolean activated;
}
