package com.example.TalentGo.service;

import com.example.TalentGo.entity.VendorClientMapping;
import com.example.TalentGo.repository.VendorClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorClientService {

    @Autowired
    private VendorClientRepository vendorClientRepository;

    public VendorClientMapping assignVendorToClient(VendorClientMapping mapping) {
        return vendorClientRepository.save(mapping);
    }

    public List<VendorClientMapping> getVendorsByClient(Long clientId) {
        return vendorClientRepository.findByClient_Id(clientId);
    }

    public List<VendorClientMapping> getClientsByVendor(Long vendorId) {
        return vendorClientRepository.findByClient_Id(vendorId);
    }
}
