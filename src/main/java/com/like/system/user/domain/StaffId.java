package com.like.system.user.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"companyCode", "staffNo"})
@Embeddable
public class StaffId implements Serializable {
	
	private static final long serialVersionUID = -8848284684753048121L;

	@Column(name="ORG_CD", insertable = false, updatable = false)
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
