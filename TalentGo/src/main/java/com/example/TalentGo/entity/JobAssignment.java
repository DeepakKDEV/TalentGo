package com.example.TalentGo.entity;

import com.example.TalentGo.enums.ApplicationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;


@Entity
@Table(name = "job_assignments", uniqueConstraints = @UniqueConstraint(columnNames = {"job_id","candidate_id"}))
public class JobAssignment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(optional = false)
    @JoinColumn(name = "job_id")
    private Job job;


    @ManyToOne(optional = false)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;


    @NotNull
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus = ApplicationStatus.APPLIED;


    @NotNull
    private Instant createdAt = Instant.now();


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }
    public Candidate getCandidate() { return candidate; }
    public void setCandidate(Candidate candidate) { this.candidate = candidate; }
    public ApplicationStatus getApplicationStatus() { return applicationStatus; }
    public void setApplicationStatus(ApplicationStatus applicationStatus) { this.applicationStatus = applicationStatus; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
