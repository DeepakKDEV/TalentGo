package com.example.TalentGo.repository;


import com.example.TalentGo.entity.JobAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobAssignmentRepository extends JpaRepository<JobAssignment, Long> {
    List<JobAssignment> findByJobId(Long jobId);
    List<JobAssignment> findByCandidateId(Long candidateId);
}

