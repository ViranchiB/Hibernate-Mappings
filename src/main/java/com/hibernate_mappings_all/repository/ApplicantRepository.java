package com.hibernate_mappings_all.repository;

import com.hibernate_mappings_all.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
