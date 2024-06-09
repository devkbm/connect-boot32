package com.like.system.menurole.export;

import java.util.List;

public interface MenuGroupByUserSelectUseCase {

	List<MenuGroupDTO> select(String companyCode, String userId);
}
