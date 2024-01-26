package com.like.system.role.application.port.out;

import com.like.system.role.domain.Role;

public interface RoleCommandDbPort {
	Role find(String companyCode, String roleCode);
	
	void save(Role entity);
	
	void delete(String companyCode, String roleCode);
}
