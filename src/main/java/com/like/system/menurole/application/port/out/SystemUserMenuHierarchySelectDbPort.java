package com.like.system.menurole.application.port.out;

import java.util.List;

import com.like.system.menu.domain.MenuHierarchy;

public interface SystemUserMenuHierarchySelectDbPort {

	List<MenuHierarchy> select(String companyCode, String menuGroupCode, List<String> roleCodes);
}
