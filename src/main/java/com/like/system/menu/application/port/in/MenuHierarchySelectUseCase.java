package com.like.system.menu.application.port.in;

import java.util.List;

import com.like.system.menu.dto.MenuHierarchyNgZorro;

public interface MenuHierarchySelectUseCase {

	List<MenuHierarchyNgZorro> select(String companyCode, String menuGroupCode);	
}
