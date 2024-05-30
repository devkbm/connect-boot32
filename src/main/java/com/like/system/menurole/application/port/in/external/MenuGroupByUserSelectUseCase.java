package com.like.system.menurole.application.port.in.external;

import java.util.List;

import com.like.system.menu.dto.MenuGroupSaveDTO;

public interface MenuGroupByUserSelectUseCase {

	List<MenuGroupSaveDTO> select(String companyCode, String userId);
}
