package com.example.TalentGo.controller;

import com.example.TalentGo.entity.Candidate;
import com.example.TalentGo.exception.ResourceNotFoundException;
import com.example.TalentGo.model.ApiResponse;
import com.example.TalentGo.service.CandidateVisibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class CandidateVisibilityController {

    @Autowired
    private CandidateVisibilityService candidateVisibilityService;

    // ADMIN, ORGADMIN, USER can view visible candidates
    @GetMapping("/{orgId}/visible-candidates")
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN','USER')")
    public ResponseEntity<ApiResponse<List<Candidate>>> getVisibleCandidates(@PathVariable Long orgId) throws ResourceNotFoundException {
        List<Candidate> candidates = candidateVisibilityService.getVisibleCandidates(orgId);
        if (candidates == null || candidates.isEmpty()) {
            throw new ResourceNotFoundException("No visible candidates found for organization id: " + orgId);
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "Visible candidates fetched successfully", candidates));
    }
}
