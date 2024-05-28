package com.like.system.menu.application.port.out;

import java.util.List;

import com.like.system.menu.domain.MenuHierarchy;

public interface MenuHierarchySelectDbPort {

	List<MenuHierarchy> getAllFlattenNodes(String companyCode, String menuGroupCode);
}
