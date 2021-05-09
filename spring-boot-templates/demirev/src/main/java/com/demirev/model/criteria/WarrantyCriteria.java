package com.demirev.model.criteria;

import java.util.Date;

public class WarrantyCriteria {

    private String id;

    private Long facilityId;

    private Long itemId;

    private String itemSerialNumber;

    private Long warrantyConditionId;

    private Long endUserId;

    private Date issueDate;

    private Integer durationMonths;

    private Date expirationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemSerialNumber() {
        return itemSerialNumber;
    }

    public void setItemSerialNumber(String itemSerialNumber) {
        this.itemSerialNumber = itemSerialNumber;
    }

    public Long getWarrantyConditionId() {
        return warrantyConditionId;
    }

    public void setWarrantyConditionId(Long warrantyConditionId) {
        this.warrantyConditionId = warrantyConditionId;
    }

    public Long getEndUserId() {
        return endUserId;
    }

    public void setEndUserId(Long endUserId) {
        this.endUserId = endUserId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Integer getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(Integer durationMonths) {
        this.durationMonths = durationMonths;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
