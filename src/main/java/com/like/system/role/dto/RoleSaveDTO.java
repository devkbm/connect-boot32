package com.like.system.role.dto;

import com.like.system.role.domain.Role;

public record RoleSaveDTO(
		String clientAppUrl,			
		String id,
		String companyCode,
		String roleCode,
		String roleName,
		String description
		) {
	
	public Role newEntity() {
		Role entity = new Role(this.companyCode, this.roleCode, this.roleName, this.description);
		
		//entity.setAppUrl(clientAppUrl);			
		
		return entity;
	}
	
	public void modifyEntity(Role authority) {			
		authority.modifyEntity(description);
		//authority.setAppUrl(clientAppUrl);
	}
}
