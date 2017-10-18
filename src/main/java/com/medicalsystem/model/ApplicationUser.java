package com.medicalsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class ApplicationUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

    @Getter @Setter
    private String fullname;

    @Getter @Setter
    private String email;

    @Column(unique = true)
    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String status;

    @Getter @Setter
    private boolean admin;

}
