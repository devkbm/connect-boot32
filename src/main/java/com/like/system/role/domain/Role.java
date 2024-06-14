package com.like.system.role.domain;

public class Role {
	
	RoleId id;
	
	String roleName;
		
	String description;		
	
	String menuGroupCode;
	
	public Role(String companyCode, String roleCode, String roleName, String description, String menuGroupCode) {		
		this.id = new RoleId(companyCode, roleCode);		
		this.roleName = roleName;
		this.description = description;		
		this.menuGroupCode = menuGroupCode;
	}	
	
	public void modifyEntity(String description) {
		this.description = description;
	}	
		
	public String getCompanyCode() {
		return this.id.getCompanyCode();
	}

	public String getRoleCode() {
		return this.id.getRoleCode();
	}
	
	public String getRoleName() {
		return this.roleName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getMenuGroupCode() {
		return menuGroupCode;
	}
}
