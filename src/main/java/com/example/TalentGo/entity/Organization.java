package com.example.TalentGo.entity;

import com.example.TalentGo.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.Instant;
import java.util.*;


@Entity
@Table(name = "organizations", indexes = {
        @Index(columnList = "companyName"),
        @Index(columnList = "city")
})
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @NotBlank
    @Size(max = 150)
    private String companyName;


    @Size(max = 300)
    private String address;


    @Size(max = 100)
    private String city;


    // store path or URL to logo
    @Size(max = 500)
    private String logo;


    @Email
    @Size(max = 150)
    private String email;


    @Size(max = 200)
    private String website;


    // reference to a user or account manager id (could be FK in future)
    private Long accountManagerId;


    @NotNull
    private Instant createdAt = Instant.now();


    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;


    // relationships
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs = new ArrayList<>();


    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Candidate> candidates = new ArrayList<>();

// getters & setters
    public Long getId() { return Id; }
    public void setId(Long id) { this.Id = id; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    public Long getAccountManagerId() { return accountManagerId; }
    public void setAccountManagerId(Long accountManagerId) { this.accountManagerId = accountManagerId; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public List<Job> getJobs() { return jobs; }
    public List<Candidate> getCandidates() { return candidates; }
}