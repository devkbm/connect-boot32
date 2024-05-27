package com.like.system.menurole.application.port.out;

import java.util.List;

import com.like.system.menu.dto.MenuHierarchyNgZorro;

public interface SystemUserMenuHierarchySelectDbPort {

	List<MenuHierarchyNgZorro> select(String companyCode, String menuGroupCode, List<String> roleCodes);
}
