package com.example.TalentGo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vendor_client_mappings", uniqueConstraints = @UniqueConstraint(columnNames = {"vendor_id","client_id"}))
public class VendorClientMapping {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(optional = false)
    @JoinColumn(name = "vendor_id")
    private Organization vendor;


    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private Organization client;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Organization getVendor() { return vendor; }
    public void setVendor(Organization vendor) { this.vendor = vendor; }
    public Organization getClient() { return client; }
    public void setClient(Organization client) { this.client = client; }
}
