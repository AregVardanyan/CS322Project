package org.example.cs322project.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "app_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;



}
