package org.example.cs322project.repository;

import org.example.cs322project.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    @Query("""
        SELECT DISTINCT c
            FROM Section s JOIN UserSection us ON  s = us.section
            JOIN User u ON us.user = u
            JOIN Course c ON s.course = c
            WHERE u.id = :userId AND us.grade <> org.example.cs322project.model.enums.Grade.F
    """)
    List<Course> getPassedByUserId(Long userId);
}
