package org.example.cs322project.model;

import jakarta.persistence.*;
import org.example.cs322project.model.enums.Grade;


@Entity
@Table
public class UserSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Section section;

    private Grade grade;
}
