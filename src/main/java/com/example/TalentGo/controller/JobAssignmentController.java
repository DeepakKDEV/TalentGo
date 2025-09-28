package com.example.TalentGo.controller;

import com.example.TalentGo.entity.JobAssignment;
import com.example.TalentGo.exception.ResourceNotFoundException;
import com.example.TalentGo.model.ApiResponse;
import com.example.TalentGo.service.JobAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-assignments")
public class JobAssignmentController {

    @Autowired
    private JobAssignmentService jobAssignmentService;

    // Only ADMIN and ORGADMIN can assign candidates
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN')")
    public ResponseEntity<ApiResponse<JobAssignment>> assignCandidate(@RequestBody JobAssignment assignment) {
        JobAssignment saved = jobAssignmentService.assignCandidate(assignment);
        return ResponseEntity.ok(new ApiResponse<>(true, "Candidate assigned successfully", saved));
    }

    // Any authenticated user can view assignments by job
    @GetMapping("/job/{jobId}")
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN','USER')")
    public ResponseEntity<ApiResponse<List<JobAssignment>>> getAssignmentsByJob(@PathVariable Long jobId) throws ResourceNotFoundException {
        List<JobAssignment> list = jobAssignmentService.getAssignmentsByJob(jobId);
        if (list == null || list.isEmpty()) {
            throw new ResourceNotFoundException("No assignments found for job id: " + jobId);
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "Assignments fetched successfully", list));
    }

    // Any authenticated user can view assignments by candidate
    @GetMapping("/candidate/{candidateId}")
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN','USER')")
    public ResponseEntity<ApiResponse<List<JobAssignment>>> getAssignmentsByCandidate(@PathVariable Long candidateId) throws ResourceNotFoundException {
        List<JobAssignment> list = jobAssignmentService.getAssignmentsByCandidate(candidateId);
        if (list == null || list.isEmpty()) {
            throw new ResourceNotFoundException("No assignments found for candidate id: " + candidateId);
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "Assignments fetched successfully", list));
    }
}
