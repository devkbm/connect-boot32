package com.like.system.menurole.application.port.in;

import java.util.List;

import com.like.system.menu.application.port.dto.MenuHierarchyResponseDTO;

public interface SystemUserMenuHierarchySelectUseCase {
	List<MenuHierarchyResponseDTO> select(String companyCode, String userId, String menuGroupCode);
}
