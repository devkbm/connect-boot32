package com.like.system.login.application.port.out;

import java.util.List;

import com.like.system.login.application.port.dto.LoginRequestDTO;
import com.like.system.menu.application.port.dto.MenuGroupSaveDTO;
import com.like.system.permission.domain.AuthenticationToken;
import com.like.system.user.domain.SystemUser;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationTokenSavePort {
	AuthenticationToken SaveAuthenticationToken(LoginRequestDTO dto, SystemUser systemUser, List<MenuGroupSaveDTO> menuGroupList, HttpServletRequest request);
}
