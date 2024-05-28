package com.like.system.menurole.application.port.out;

import java.util.List;

import com.like.system.menurole.domain.MenuRoleHierarchy;

public interface MenuRoleHierarchySelect2DbPort {
	List<MenuRoleHierarchy> select(String companyCode, String menuGroupCode, String roleCode);
}
