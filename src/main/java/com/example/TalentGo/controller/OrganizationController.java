package com.example.TalentGo.controller;

import com.example.TalentGo.entity.Organization;
import com.example.TalentGo.exception.ResourceNotFoundException;
import com.example.TalentGo.model.ApiResponse;
import com.example.TalentGo.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    // Only ADMIN can create organization
    @PostMapping("/post")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Organization>> createOrganization(@RequestBody Organization organization) {
        Organization saved = organizationService.createOrganization(organization);
        return ResponseEntity.ok(new ApiResponse<>(true, "Organization created successfully", saved));
    }

    // All roles can view organization by id
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN','USER')")
    public ResponseEntity<ApiResponse<Organization>> getOrganizationById(@PathVariable Long id) throws ResourceNotFoundException {
        Organization org = organizationService.getOrganizationById(id);
        if (org == null) throw new ResourceNotFoundException("Organization not found with id: " + id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Organization fetched successfully", org));
    }

    // All roles can view all organizations
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN','USER')")
    public ResponseEntity<ApiResponse<List<Organization>>> getAllOrganizations() {
        List<Organization> list = organizationService.getAllOrganizations();
        return ResponseEntity.ok(new ApiResponse<>(true, "Organizations fetched successfully", list));
    }

    // ADMIN or ORGADMIN can update organization
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN')")
    public ResponseEntity<ApiResponse<Organization>> updateOrganization(@PathVariable Long id, @RequestBody Organization organization) throws ResourceNotFoundException {
        Organization updated = organizationService.updateOrganization(id, organization);
        if (updated == null) throw new ResourceNotFoundException("Organization not found with id: " + id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Organization updated successfully", updated));
    }
}
