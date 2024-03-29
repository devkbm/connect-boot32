package com.like.system.menu.application.port.out;

import java.util.List;

import com.like.system.menu.application.port.dto.MenuHierarchyResponseDTO;

public interface SystemUserMenuHierarchySelectDbPort {

	List<MenuHierarchyResponseDTO> select(String companyCode, String menuGroupCode, List<String> roleCodes);
}
