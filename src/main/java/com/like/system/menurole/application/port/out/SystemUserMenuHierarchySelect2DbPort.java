package com.like.system.menurole.application.port.out;

import java.util.List;

import com.like.system.menurole.domain.MenuRoleHierarchy;

public interface SystemUserMenuHierarchySelect2DbPort {

	List<MenuRoleHierarchy> select(String companyCode, String menuGroupCode, List<String> roleCodes);
}
