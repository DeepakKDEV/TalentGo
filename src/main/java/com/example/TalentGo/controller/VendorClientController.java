package com.example.TalentGo.controller;

import com.example.TalentGo.entity.VendorClientMapping;
import com.example.TalentGo.exception.ResourceNotFoundException;
import com.example.TalentGo.model.ApiResponse;
import com.example.TalentGo.service.VendorClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-client")
public class VendorClientController {

    @Autowired
    private VendorClientService vendorClientService;

    // Only ADMIN or ORGADMIN can assign vendor
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN')")
    public ResponseEntity<ApiResponse<VendorClientMapping>> assignVendor(@RequestBody VendorClientMapping mapping) {
        VendorClientMapping saved = vendorClientService.assignVendorToClient(mapping);
        return ResponseEntity.ok(new ApiResponse<>(true, "Vendor assigned successfully", saved));
    }

    // All roles can view vendors for a client
    @GetMapping("/client/{clientId}")
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN','USER')")
    public ResponseEntity<ApiResponse<List<VendorClientMapping>>> getVendorsForClient(@PathVariable Long clientId) {
        List<VendorClientMapping> list = vendorClientService.getVendorsByClient(clientId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Vendors fetched successfully", list));
    }

    // All roles can view clients for a vendor
    @GetMapping("/vendor/{vendorId}")
    @PreAuthorize("hasAnyRole('ADMIN','ORGADMIN','USER')")
    public ResponseEntity<ApiResponse<List<VendorClientMapping>>> getClientsForVendor(@PathVariable Long vendorId) {
        List<VendorClientMapping> list = vendorClientService.getClientsByVendor(vendorId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Clients fetched successfully", list));
    }
}
