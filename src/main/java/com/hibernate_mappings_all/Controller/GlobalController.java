package com.hibernate_mappings_all.Controller;

import com.hibernate_mappings_all.entity.Applicant;
import com.hibernate_mappings_all.entity.Resume;
import com.hibernate_mappings_all.repository.ApplicantRepository;
import com.hibernate_mappings_all.repository.ResumeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GlobalController {

    private final ApplicantRepository applicantRepository;
    private final ResumeRepository resumeRepository;

    public GlobalController(ApplicantRepository applicantRepository, ResumeRepository resumeRepository) {
        this.applicantRepository = applicantRepository;
        this.resumeRepository = resumeRepository;
    }

    @PostMapping("/applicant")
    public ResponseEntity<Applicant> createApplicant(@RequestBody Applicant applicant) {
        /*
            1. Extract child from parent
            2. Check if it's null or not
            2. If not, assign parent to child for the bidirectional mapping
         */
        Resume resume = applicant.getResume();
        if(resume != null)
            resume.setApplicant(applicant);

        return new ResponseEntity<>(applicantRepository.save(applicant), HttpStatus.CREATED);
    }

    @GetMapping("/resume/{resumeId}")
    public ResponseEntity<Resume> getResume(@PathVariable("resumeId") Long resumeId) {
        Resume resumeWithApplicant = this.resumeRepository.findResumeWithApplicant(resumeId);
        return new ResponseEntity<>(resumeWithApplicant, HttpStatus.OK);
    }

    @DeleteMapping("/deleteApplicant/{applicantId}")
    public ResponseEntity deleteApplicant(@PathVariable("applicantId") Long applicantId) {
        this.applicantRepository.deleteById(applicantId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
