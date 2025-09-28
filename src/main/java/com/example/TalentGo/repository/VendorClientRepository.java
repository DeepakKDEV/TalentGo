package com.example.TalentGo.repository;

import com.example.TalentGo.entity.VendorClientMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorClientRepository extends JpaRepository<VendorClientMapping, Long> {
    List<VendorClientMapping> findByClient_Id(Long clientId);
    List<VendorClientMapping> findByVendor_Id(Long vendorId);
}