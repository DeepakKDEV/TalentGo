package com.example.TalentGo.controller;

import com.example.TalentGo.entity.Candidate;
import com.example.TalentGo.exception.ResourceNotFoundException;
import com.example.TalentGo.service.CandidateService;
import com.example.TalentGo.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    // Only ADMIN and ORGADMIN can create candidate
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN')")
    public ResponseEntity<ApiResponse<Candidate>> createCandidate(@RequestBody Candidate candidate) {
        Candidate saved = candidateService.createCandidate(candidate);
        return ResponseEntity.ok(new ApiResponse<>(true, "Candidate created successfully", saved));
    }

    // Any authenticated user can view candidate
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN','USER')")
    public ResponseEntity<ApiResponse<Candidate>> getCandidateById(@PathVariable Long id) throws ResourceNotFoundException {
        Candidate candidate = candidateService.getCandidateById(id);
        if (candidate == null) throw new ResourceNotFoundException("Candidate not found with id: " + id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Candidate fetched successfully", candidate));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN','USER')")
    public ResponseEntity<ApiResponse<List<Candidate>>> getAllCandidates() {
        List<Candidate> list = candidateService.getAllCandidates();
        return ResponseEntity.ok(new ApiResponse<>(true, "Candidates fetched successfully", list));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN')")
    public ResponseEntity<ApiResponse<Candidate>> updateCandidate(@PathVariable Long id,
                                                                  @RequestBody Candidate candidate) throws ResourceNotFoundException {
        Candidate updated = candidateService.updateCandidate(id, candidate);
        if (updated == null) throw new ResourceNotFoundException("Candidate not found with id: " + id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Candidate updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteCandidate(@PathVariable Long id) throws ResourceNotFoundException {
        boolean deleted = candidateService.deleteCandidate(id);
        if (!deleted) throw new ResourceNotFoundException("Candidate not found with id: " + id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Candidate deleted successfully", null));
    }

    @GetMapping("/organization/{orgId}")
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN','USER')")
    public ResponseEntity<ApiResponse<List<Candidate>>> getCandidatesByOrganization(@PathVariable Long orgId) {
        List<Candidate> list = candidateService.getCandidatesByOrganization(orgId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Candidates fetched successfully", list));
    }
}
