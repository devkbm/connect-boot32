package com.like.system.user.application.port.in;

import java.util.List;

import com.like.system.menurole.external.MenuHierarchyNgZorroDTO;

public interface SystemUserMenuHierarchyQueryUseCase {
	List<MenuHierarchyNgZorroDTO> select(String companyCode, String userId, String menuGroupCode);
}
