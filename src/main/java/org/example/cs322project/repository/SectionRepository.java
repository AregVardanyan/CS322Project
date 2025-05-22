package org.example.cs322project.repository;

import org.example.cs322project.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Long> {
}
