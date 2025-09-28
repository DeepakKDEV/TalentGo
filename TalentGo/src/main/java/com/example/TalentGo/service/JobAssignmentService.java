package com.example.TalentGo.service;

import com.example.TalentGo.entity.JobAssignment;
import com.example.TalentGo.repository.JobAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAssignmentService {

    @Autowired
    private JobAssignmentRepository jobAssignmentRepository;

    public JobAssignment assignCandidate(JobAssignment assignment) {
        return jobAssignmentRepository.save(assignment);
    }

    public List<JobAssignment> getAssignmentsByJob(Long jobId) {
        return jobAssignmentRepository.findByJobId(jobId);
    }

    public List<JobAssignment> getAssignmentsByCandidate(Long candidateId) {
        return jobAssignmentRepository.findByCandidateId(candidateId);
    }
}
