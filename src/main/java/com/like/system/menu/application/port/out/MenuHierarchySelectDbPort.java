package com.like.system.menu.application.port.out;

import java.util.List;

import com.like.system.menu.dto.MenuHierarchyNgZorro;

public interface MenuHierarchySelectDbPort {
	List<MenuHierarchyNgZorro> select(String companyCode, String menuGroupCode);
}
