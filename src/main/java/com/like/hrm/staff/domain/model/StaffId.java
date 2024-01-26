package com.like.hrm.staff.domain.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"companyCode", "staffNo"})
@Embeddable
public class StaffId implements Serializable {

	private static final long serialVersionUID = 6064949234611151198L;

	@Column(name="ORG_CD")
	private String companyCode;
	
	@Column(name="STAFF_NO")
	private String staffNo;

	protected StaffId() {}
	
	public StaffId(String companyCode, String staffNo) {
		this.companyCode = companyCode;
		this.staffNo = staffNo;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public String getStaffNo() {
		return staffNo;
	}		
}
