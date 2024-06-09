package com.like.system.menurole.export;

import java.util.List;

public interface MenuHierarchyByRolesSelectUseCase {

	List<MenuHierarchyNgZorroDTO> select(String companyCode, String menuGroupCode, List<String> roleCodes);
}
