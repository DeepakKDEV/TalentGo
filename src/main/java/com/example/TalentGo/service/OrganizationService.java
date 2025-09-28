package com.example.TalentGo.service;

import com.example.TalentGo.entity.Organization;
import com.example.TalentGo.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    public Organization getOrganizationById(Long id) {
        return organizationRepository.findById(id).orElse(null);
    }

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Organization updateOrganization(Long id, Organization organization) {
        Organization existing = organizationRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setCompanyName(organization.getCompanyName());
            existing.setAddress(organization.getAddress());
            existing.setCity(organization.getCity());
            existing.setLogo(organization.getLogo());
            existing.setEmail(organization.getEmail());
            existing.setWebsite(organization.getWebsite());
            existing.setAccountManagerId(organization.getAccountManagerId());
            existing.setStatus(organization.getStatus());
            return organizationRepository.save(existing);
        }
        return null;
    }
}

