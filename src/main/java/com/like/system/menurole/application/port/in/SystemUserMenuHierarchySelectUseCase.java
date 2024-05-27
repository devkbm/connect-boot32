package com.like.system.menurole.application.port.in;

import java.util.List;

import com.like.system.menu.dto.MenuHierarchyNgZorro;

public interface SystemUserMenuHierarchySelectUseCase {
	List<MenuHierarchyNgZorro> select(String companyCode, String userId, String menuGroupCode);
}
