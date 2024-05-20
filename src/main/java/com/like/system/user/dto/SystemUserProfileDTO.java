package com.like.system.user.dto;

import java.util.Optional;

import com.like.system.dept.domain.Dept;
import com.like.system.user.domain.SystemUser;

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
		SystemUserProfileSessionDTO session
		) {

	public static SystemUserProfileDTO toDTO(SystemUser entity, SystemUserProfileSessionDTO session) {
		
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
 			    .session(session)
 			    /*
 			    .ipAddress(WebRequestUtil.getIpAddress(request))
 			    .lastAccessedTime(new Date(request.getSession().getLastAccessedTime()))
 			    */
				.build();
	}
}
