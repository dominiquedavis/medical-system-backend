package com.medicalsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User extends IdComparableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Getter @Setter
    private long id;

    @Column(name = "FULL_NAME")
    @Getter @Setter
    private String fullName;

    @Column(name = "EMAIL")
    @Getter @Setter
    private String email;

    @Column(name = "USERNAME", unique = true)
    @Getter @Setter
    private String username;

    @Column(name = "PASSWORD")
    @Getter @Setter
    private String password;

    @Column(name = "STATUS")
    @Getter @Setter
    private String status;

    @Column(name = "ADMIN")
    @Getter @Setter
    private boolean admin;

}
