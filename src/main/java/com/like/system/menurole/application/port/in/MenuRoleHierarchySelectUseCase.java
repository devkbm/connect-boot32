package com.like.system.menurole.application.port.in;

import java.util.List;

import com.like.system.menurole.dto.MenuRoleMappingHierarchyResponseDTO;

public interface MenuRoleHierarchySelectUseCase {

	List<MenuRoleMappingHierarchyResponseDTO> select(String companyCode, String menuGroupCode, String roleCode);
}
