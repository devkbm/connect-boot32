package com.like.system.menu.application.port.in;

import java.util.List;

import com.like.system.menu.dto.MenuHierarchyResponseDTO;

public interface MenuHierarchySelectUseCase {

	List<MenuHierarchyResponseDTO> select(String companyCode, String menuGroupCode);	
}
