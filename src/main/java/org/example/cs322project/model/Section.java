package org.example.cs322project.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.cs322project.model.enums.Hours;
import org.example.cs322project.model.enums.Schedule;
import org.example.cs322project.model.enums.Semester;


@Data
@Entity
@Table
public class Section{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    private Integer year;

    @Enumerated(EnumType.STRING)
    private Semester semester;

    @Enumerated(EnumType.STRING)
    private Schedule schedule;

    @Enumerated(EnumType.STRING)
    private Hours hours;
}
