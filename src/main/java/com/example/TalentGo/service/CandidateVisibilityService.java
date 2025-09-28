package com.example.TalentGo.service;

import com.example.TalentGo.entity.Candidate;
import com.example.TalentGo.entity.JobAssignment;
import com.example.TalentGo.repository.CandidateRepository;
import com.example.TalentGo.repository.JobAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateVisibilityService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobAssignmentRepository jobAssignmentRepository;

    public List<Candidate> getVisibleCandidates(Long orgId) {
        List<Candidate> visibleCandidates = new ArrayList<>();

        // 1️⃣ Own candidates
        visibleCandidates.addAll(candidateRepository.findByOrganizationId(orgId));

        // 2️⃣ Candidates assigned to this organization's jobs (from vendors)
        List<JobAssignment> assignments = jobAssignmentRepository.findByJobId(orgId);
        for (JobAssignment assignment : assignments) {
            Candidate candidate = candidateRepository.findById(assignment.getJob().getId()).orElse(null);
            if (candidate != null && !visibleCandidates.contains(candidate)) {
                visibleCandidates.add(candidate);
            }
        }

        return visibleCandidates;
    }
}
