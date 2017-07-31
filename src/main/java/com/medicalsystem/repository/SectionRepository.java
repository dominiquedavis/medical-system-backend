package com.medicalsystem.repository;

import com.medicalsystem.model.Section;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;

@Lazy
public interface SectionRepository extends JpaRepository<Section, Integer> {
}
