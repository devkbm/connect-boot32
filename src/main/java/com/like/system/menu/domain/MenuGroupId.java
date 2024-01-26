package com.like.system.menu.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class MenuGroupId implements Serializable {

	private static final long serialVersionUID = 9113349756522741742L;

	@Column(name="ORG_CD")
	String companyCode;
		
	@Column(name="MENU_GROUP_CD")
	String menuGroupCode;
	
	protected MenuGroupId() {}

	public MenuGroupId(String companyCode, String menuGroupCode) {		
		this.companyCode = companyCode;
		this.menuGroupCode = menuGroupCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public String getMenuGroupCode() {
		return menuGroupCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(companyCode, menuGroupCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuGroupId other = (MenuGroupId) obj;
		return Objects.equals(companyCode, other.companyCode)
			&& Objects.equals(menuGroupCode, other.menuGroupCode);
	}
	
	
}
