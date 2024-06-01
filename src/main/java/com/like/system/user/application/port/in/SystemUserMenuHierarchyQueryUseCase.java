package com.like.system.user.application.port.in;

import java.util.List;

import com.like.system.menu.dto.MenuHierarchyNgZorro;

public interface SystemUserMenuHierarchyQueryUseCase {
	List<MenuHierarchyNgZorro> select(String companyCode, String userId, String menuGroupCode);
}