package com.example.TalentGo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "candidates", indexes = @Index(columnList = "email"))
public class Candidate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Size(max = 100)
    private String firstName;


    @Size(max = 100)
    private String lastName;


    @Email
    @NotBlank
    @Size(max = 150)
    private String email;


   // @Pattern(regexp = "^\\+?[0-9 .\-]{7,25}$", message = "invalid phone number")
    private String phoneNumber;


    // a reference or path to stored resume
    @Size(max = 500)
    private String resumeRef;


    @Size(max = 500)
    private String primarySkills;


    @Min(0)
    private Double totalExperience; // in years


    @Size(max = 100)
    private String currentLocation;


    @Size(max = 100)
    private String preferredLocation;


    @ManyToOne(optional = false)
    @JoinColumn(name = "organization_id")
    private Organization organization; // owner/org that registered candidate


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getResumeRef() { return resumeRef; }
    public void setResumeRef(String resumeRef) { this.resumeRef = resumeRef; }
    public String getPrimarySkills() { return primarySkills; }
    public void setPrimarySkills(String primarySkills) { this.primarySkills = primarySkills; }
    public Double getTotalExperience() { return totalExperience; }
    public void setTotalExperience(Double totalExperience) { this.totalExperience = totalExperience; }
    public String getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(String currentLocation) { this.currentLocation = currentLocation; }
    public String getPreferredLocation() { return preferredLocation; }
    public void setPreferredLocation(String preferredLocation) { this.preferredLocation = preferredLocation; }
    public Organization getOrganization() { return organization; }
    public void setOrganization(Organization organization) { this.organization = organization; }
}
