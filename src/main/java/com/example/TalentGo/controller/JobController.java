package com.example.TalentGo.controller;

import com.example.TalentGo.entity.Job;
import com.example.TalentGo.exception.ResourceNotFoundException;
import com.example.TalentGo.model.ApiResponse;
import com.example.TalentGo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // ADMIN or ORGADMIN can create job
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN')")
    public ResponseEntity<ApiResponse<Job>> createJob(@RequestBody Job job) {
        Job saved = jobService.createJob(job);
        return ResponseEntity.ok(new ApiResponse<>(true, "Job created successfully", saved));
    }

    // All roles can view job by id
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN','USER')")
    public ResponseEntity<ApiResponse<Job>> getJobById(@PathVariable Long id) throws ResourceNotFoundException {
        Job job = jobService.getJobById(id);
        if (job == null) throw new ResourceNotFoundException("Job not found with id: " + id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Job fetched successfully", job));
    }

    // All roles can view all jobs
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN','USER')")
    public ResponseEntity<ApiResponse<List<Job>>> getAllJobs() {
        List<Job> list = jobService.getAllJobs();
        return ResponseEntity.ok(new ApiResponse<>(true, "Jobs fetched successfully", list));
    }

    // All roles can view jobs by organization
    @GetMapping("/organization/{orgId}")
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN','USER')")
    public ResponseEntity<ApiResponse<List<Job>>> getJobsByOrganization(@PathVariable Long orgId) {
        List<Job> list = jobService.getJobsByOrganizationId(orgId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Jobs fetched successfully", list));
    }

    // ADMIN or ORGADMIN can update job
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN')")
    public ResponseEntity<ApiResponse<Job>> updateJob(@PathVariable Long id, @RequestBody Job job) throws ResourceNotFoundException {
        Job updated = jobService.updateJob(id, job);
        if (updated == null) throw new ResourceNotFoundException("Job not found with id: " + id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Job updated successfully", updated));
    }
}
