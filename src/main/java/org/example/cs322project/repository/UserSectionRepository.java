package org.example.cs322project.repository;

import org.example.cs322project.model.UserSection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSectionRepository extends JpaRepository<UserSection, Long> {
}
