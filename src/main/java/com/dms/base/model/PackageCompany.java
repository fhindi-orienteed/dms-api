package com.dms.base.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "packages_companies")
public class PackageCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "id")
    private Packages packages;
    
    public PackageCompany() {}
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Packages getUser() { return packages; }
    public void setUser(Packages packages) { this.packages = packages; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }
}
