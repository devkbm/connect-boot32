package com.like.system.role.adapter.out.persistence.jpa.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"companyCode", "roleCode"})
@Embeddable
public class JpaRoleId implements Serializable {
		
	private static final long serialVersionUID = 6334573408608466739L;

	@Column(name="ORG_CD")
	String companyCode;
	
	@Column(name="ROLE_CD")
	String roleCode;

	protected JpaRoleId() {}
	
	public JpaRoleId(String companyCode, String roleCode) {
		this.companyCode = companyCode;
		this.roleCode = roleCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public String getRoleCode() {
		return roleCode;
	}
	
	
}
