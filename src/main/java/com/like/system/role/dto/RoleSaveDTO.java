package com.like.system.role.dto;

import com.like.system.role.domain.Role;

public record RoleSaveDTO(
		String clientAppUrl,			
		String id,
		String companyCode,
		String roleCode,
		String roleName,
		String description,
		String menuGroupCode
		) {
	
	public Role newEntity() {
		Role entity = new Role(this.companyCode, this.roleCode, this.roleName, this.description, this.menuGroupCode);
		
		//entity.setAppUrl(clientAppUrl);			
		
		return entity;
	}
	
	public void modifyEntity(Role authority) {		
		authority.modifyEntity(roleName, description, menuGroupCode);
		
		//authority.setAppUrl(clientAppUrl);
	}
}
