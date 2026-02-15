package com.hackathon_iste.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class users {
    Long user_id;
    String name;
    String email;
    String password;
    String city;
    String country;
    String phone;
    int active_yn;

}
