package com.hibernate_mappings_all.repository;

import com.hibernate_mappings_all.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("SELECT a FROM Application a LEFT JOIN FETCH a.applicant WHERE a.applicationId = :applicationId")
    Application findApplicationWithApplicant(@Param("applicationId") Long applicationId);

}
