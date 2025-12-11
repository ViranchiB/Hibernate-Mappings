package com.hibernate_mappings_all.repository;

import com.hibernate_mappings_all.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResumeRepository extends JpaRepository<Resume,Long> {

    @Query("SELECT r FROM Resume r LEFT JOIN FETCH r.applicant WHERE r.resumeId = :resumeId")
    Resume findResumeWithApplicant(@Param("resumeId") Long resumeId);

}
