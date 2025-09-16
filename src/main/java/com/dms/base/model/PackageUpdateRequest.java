package com.dms.base.model;

import com.dms.base.util.Constant.UpdateRequestStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "package_update_requests")
public class PackageUpdateRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long packageId;

    private long submitBy;

    @Column(length = 2048)
    private String dataJson;

    private UpdateRequestStatus requestStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPackageId() {
        return packageId;
    }

    public void setPackageId(long packageId) {
        this.packageId = packageId;
    }

    public long getSubmitBy() {
        return submitBy;
    }

    public void setSubmitBy(long submitBy) {
        this.submitBy = submitBy;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }

    public UpdateRequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(UpdateRequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}