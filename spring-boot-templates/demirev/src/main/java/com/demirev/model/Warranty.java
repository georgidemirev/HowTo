package com.demirev.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "warranty")
public class Warranty {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private String itemSerialNumber;

    @ManyToOne
    @JoinColumn(name = "warranty_condition_id")
    private WarrantyCondition warrantyCondition;

    private Long endUserId;

    private Date issueDate;

    private Integer durationMonths;

    private Date expirationDate;

    public Warranty() {
    }

    public Warranty(String id, Facility facility, Item item, String itemSerialNumber,
                    WarrantyCondition warrantyCondition, Long endUserId, Date issueDate, int durationMonths,
                    Date expirationDate) {
        this.id = id;
        this.facility = facility;
        this.item = item;
        this.itemSerialNumber = itemSerialNumber;
        this.warrantyCondition = warrantyCondition;
        this.endUserId = endUserId;
        this.issueDate = issueDate;
        this.durationMonths = durationMonths;
        this.expirationDate = expirationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getItemSerialNumber() {
        return itemSerialNumber;
    }

    public void setItemSerialNumber(String itemSerialNumber) {
        this.itemSerialNumber = itemSerialNumber;
    }

    public WarrantyCondition getWarrantyCondition() {
        return warrantyCondition;
    }

    public void setWarrantyCondition(WarrantyCondition warrantyCondition) {
        this.warrantyCondition = warrantyCondition;
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

	@Override
	public String toString() {
		return "Warranty{" +
				"id='" + id + '\'' +
				", facility=" + facility +
				", item=" + item +
				", itemSerialNumber='" + itemSerialNumber + '\'' +
				", warrantyCondition=" + warrantyCondition +
				", endUserId=" + endUserId +
				", issueDate=" + issueDate +
				", durationMonths=" + durationMonths +
				", expirationDate=" + expirationDate +
				'}';
	}
}
