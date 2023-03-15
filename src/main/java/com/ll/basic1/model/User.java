package com.ll.basic1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    Long id;

    String username;
    Long password;

    public User(String username, Long password) {
        this.username = username;
        this.password = password;
    }
}
