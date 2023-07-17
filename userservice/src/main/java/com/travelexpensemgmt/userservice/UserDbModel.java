package com.travelexpensemgmt.userservice;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@Entity(name = "Users")
@Table(name="Users")
@AllArgsConstructor
@ToString
public class UserDbModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;
    @Column(name="mail")
    private String mail;

}
