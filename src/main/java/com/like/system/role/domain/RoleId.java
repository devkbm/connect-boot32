package com.like.system.role.domain;

import java.util.Objects;

public class RoleId {

	String companyCode;
	
	String roleCode;
	
	public RoleId(String companyCode, String roleCode) {
		this.companyCode = companyCode;
		this.roleCode = roleCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleCode, companyCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleId other = (RoleId) obj;
		return Objects.equals(roleCode, other.roleCode)
				&& Objects.equals(companyCode, other.companyCode);
	}
	
	
}
