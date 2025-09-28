package com.example.TalentGo.service;

import com.example.TalentGo.entity.Job;
import com.example.TalentGo.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> getJobsByOrganizationId(Long orgId) {
        return jobRepository.findByOrganizationId(orgId);
    }

    public Job updateJob(Long id, Job job) {
        Job existing = jobRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setJobTitle(job.getJobTitle());
            existing.setDescription(job.getDescription());
            existing.setRequiredSkills(job.getRequiredSkills());
            existing.setExperienceLevel(job.getExperienceLevel());
            existing.setStatus(job.getStatus());
            return jobRepository.save(existing);
        }
        return null;
    }
}

