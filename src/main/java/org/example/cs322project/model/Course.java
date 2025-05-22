package org.example.cs322project.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

@Data
@Entity
@Table
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String faculty;

    private String code;

    @ManyToMany
    @JoinTable(
            name = "course_prereq",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "prerequisite_id")
    )
    private List<Course> prerequisites;

    private String topic;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Section> sections;
}
