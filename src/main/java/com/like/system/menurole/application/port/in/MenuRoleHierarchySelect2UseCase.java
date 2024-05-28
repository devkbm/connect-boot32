package com.like.system.menurole.application.port.in;

import java.util.List;

public interface MenuRoleHierarchySelect2UseCase {
	List<?> select(String companyCode, String menuGroupCode, String roleCode);
}
