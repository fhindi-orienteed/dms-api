package com.dms.base.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 

    private String name;
    private String email;
    private String phone;
    private String role;
    private String password;
    private boolean locked;
}
