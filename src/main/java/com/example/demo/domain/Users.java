package com.example.demo.domain;


import com.example.demo.customClass.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private  String username;
    @Email
    private String userEmail;
    private String address;
    private String phonenumber;
    @Enumerated(EnumType.STRING)
    private UserType userType;


    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnoreProperties({"user"})
    private List<items> enrolleditems = new ArrayList<>();

}
