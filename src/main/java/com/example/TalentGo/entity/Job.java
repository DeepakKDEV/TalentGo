package com.example.TalentGo.entity;

import com.example.TalentGo.enums.ExperienceLevel;
import com.example.TalentGo.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.Instant;
import java.util.*;


@Entity
@Table(name = "jobs", indexes = @Index(columnList = "jobTitle"))
public class Job {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Size(max = 200)
    private String jobTitle;


    @Size(max = 4000)
    private String description;


    // simple comma-separated skills - can be normalized to a join table if needed
    @Size(max = 1000)
    private String requiredSkills;


    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;


    @ManyToOne(optional = false)
    @JoinColumn(name = "organization_id")
    private Organization organization;


    @NotNull
    private Instant createdAt = Instant.now();


    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE; // interpret as OPEN/ACTIVE; client can close by setting INACTIVE


    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getRequiredSkills() { return requiredSkills; }
    public void setRequiredSkills(String requiredSkills) { this.requiredSkills = requiredSkills; }
    public ExperienceLevel getExperienceLevel() { return experienceLevel; }
    public void setExperienceLevel(ExperienceLevel experienceLevel) { this.experienceLevel = experienceLevel; }
    public Organization getOrganization() { return organization; }
    public void setOrganization(Organization organization) { this.organization = organization; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
