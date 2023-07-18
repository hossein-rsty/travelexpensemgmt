package com.travelexpensemgmt.userservice;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
@AllArgsConstructor
@Builder
@Getter
public class UserDbModel {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique=true)
    private String mail;
}
