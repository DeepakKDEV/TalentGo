package com.example.TalentGo.repository;

import com.example.TalentGo.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByOrganizationId(Long organizationId);
}

