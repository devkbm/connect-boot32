package com.like.system.menurole.application.port.in.external;

import java.util.List;

import com.like.system.menu.dto.MenuHierarchyNgZorro;

public interface MenuHierarchyByRolesSelectUseCase {

	List<MenuHierarchyNgZorro> select(String companyCode, String menuGroupCode, List<String> roleCodes);
}
