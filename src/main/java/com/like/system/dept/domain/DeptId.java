package com.like.system.dept.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"companyCode", "deptCode"})
@Embeddable
public class DeptId implements Serializable {
	
	private static final long serialVersionUID = 5869853950528008537L;

	@Column(name="ORG_CD")
	private String companyCode;
	
	@Column(name = "DEPT_CD", nullable = false)
	private String deptCode;
	
	protected DeptId() {}
	
	public DeptId(String companyCode, String deptCode) {
		this.companyCode = companyCode;
		this.deptCode = deptCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public String getDeptCode() {
		return deptCode;
	}
}
