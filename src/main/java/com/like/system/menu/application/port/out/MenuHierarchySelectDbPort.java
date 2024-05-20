package com.like.system.menu.application.port.out;

import java.util.List;

import com.like.system.menu.dto.MenuHierarchyResponseDTO;

public interface MenuHierarchySelectDbPort {
	List<MenuHierarchyResponseDTO> select(String companyCode, String menuGroupCode);
}
