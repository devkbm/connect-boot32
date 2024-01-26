package com.like.system.menu.application.port.out;

import java.util.List;

import com.like.system.menu.application.port.dto.MenuRoleMappingHierarchyResponseDTO;

public interface MenuRoleHierarchySelectDbPort {
	List<MenuRoleMappingHierarchyResponseDTO> select(String companyCode, String menuGroupCode, String roleCode);
}
