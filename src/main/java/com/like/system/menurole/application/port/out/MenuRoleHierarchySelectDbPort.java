package com.like.system.menurole.application.port.out;

import java.util.List;

import com.like.system.menurole.dto.MenuRoleHierarchyNgZorro;

public interface MenuRoleHierarchySelectDbPort {
	List<MenuRoleHierarchyNgZorro> select(String companyCode, String menuGroupCode, String roleCode);
}
