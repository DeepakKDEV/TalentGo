package com.example.TalentGo.service;

import com.example.TalentGo.entity.Candidate;
import com.example.TalentGo.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public Candidate createCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate updateCandidate(Long id, Candidate candidate) {
        Candidate existing = candidateRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setFirstName(candidate.getFirstName());
            existing.setLastName(candidate.getLastName());
            existing.setEmail(candidate.getEmail());
            existing.setPhoneNumber(candidate.getPhoneNumber());
            existing.setResumeRef(candidate.getResumeRef());
            existing.setPrimarySkills(candidate.getPrimarySkills());
            existing.setTotalExperience(candidate.getTotalExperience());
            existing.setCurrentLocation(candidate.getCurrentLocation());
            existing.setPreferredLocation(candidate.getPreferredLocation());
            existing.setOrganization(candidate.getOrganization());
            return candidateRepository.save(existing);
        }
        return null;
    }

    public boolean deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
        return false;
    }

    public List<Candidate> getCandidatesByOrganization(Long orgId) {
        return candidateRepository.findByOrganizationId(orgId);
    }
}

