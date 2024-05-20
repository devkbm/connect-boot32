package com.like.system.menurole.application.port.in;

import java.util.List;

import com.like.system.menu.dto.MenuGroupSaveDTO;

public interface SystemUserMenuGroupSelectUseCase {

	List<MenuGroupSaveDTO> select(String companyCode, String userId);
}
