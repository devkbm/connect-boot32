package com.like.system.user.application.port.dto;

import java.util.Date;
import java.util.Optional;

import com.like.system.core.web.util.WebRequestUtil;
import com.like.system.dept.domain.Dept;
import com.like.system.user.domain.SystemUser;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;

@Builder
public record SystemUserProfileDTO(
		String companyCode,
		String userId,
		String staffNo,
		String staffName,
		String deptCode,
		String deptName,
		String mobileNum,
		String email,
		String ipAddress,
		Date lastAccessedTime
		) {

	public static SystemUserProfileDTO toDTO(SystemUser entity, HttpServletRequest request) {
		
		Optional<Dept> dept = Optional.ofNullable(entity.getDept());			
						
		return SystemUserProfileDTO
				.builder()
				.companyCode(entity.getStaffId().getCompanyCode())
			    .userId(entity.getId().getUserId())
 			    .staffNo(entity.getStaffId().getStaffNo())
			    .staffName(entity.getName())												   
			    .deptCode(dept.map(r -> r.getId().getDeptCode()).orElse(""))
			    .deptName(dept.map(Dept::getDeptNameKorean).orElse(""))
			    .mobileNum(entity.getMobileNum())
 			    .email(entity.getEmail())
 			    .ipAddress(WebRequestUtil.getIpAddress(request))
 			    .lastAccessedTime(new Date(request.getSession().getLastAccessedTime()))
				.build();
	}
}
