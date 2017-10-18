package com.medicalsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
public class ApplicationUser {

    @Getter @Setter
    private long id;

    @Getter @Setter
    private String fullname;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String status;

    @Getter @Setter
    private boolean admin;

}
