package com.like.system.menurole.external;

import java.util.List;

public interface MenuHierarchyByRolesSelectUseCase {

	List<MenuHierarchyNgZorro> select(String companyCode, String menuGroupCode, List<String> roleCodes);
}
