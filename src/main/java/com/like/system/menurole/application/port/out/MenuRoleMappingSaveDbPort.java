package com.like.system.menurole.application.port.out;

import java.util.List;

import com.like.system.menurole.domain.MenuRoleMapping;

public interface MenuRoleMappingSaveDbPort {

	void clear(String orgnizationCode, String menuGroupCode, String roleCode);
	
	void save(List<MenuRoleMapping> entityList);
}
