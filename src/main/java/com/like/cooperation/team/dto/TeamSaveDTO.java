package com.like.cooperation.team.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.like.cooperation.team.domain.Team;
import com.like.system.user.export.SystemUserDTOSelectUseCase;

import lombok.Builder;

@Builder
public record TeamSaveDTO(
		LocalDateTime createdDt,
		String createdBy,
		LocalDateTime modifiedDt,
		String modifiedBy,
		String clientAppUrl,
		String companyCode,
		Long teamId,
		String teamName,
		List<String> memberList
		) {
	
	public Team newEntity(SystemUserDTOSelectUseCase service) {						
		Team entity = null;
					
		if (memberList == null || memberList.isEmpty()) {
			entity = new Team(teamName);
		} else {			
			entity = new Team(teamName, this.memberList);
		}										
		
		entity.setAppUrl(clientAppUrl);
		
		return entity;
	}
	
	public Team modify(Team entity, SystemUserDTOSelectUseCase service) {
		entity.modify(teamName);
									
		entity.updateMembers(this.memberList);
		
		entity.setAppUrl(clientAppUrl);
		
		return entity;
	}
	
	public static TeamSaveDTO toDTO(Team entity) {					
		
		if (entity == null) return null;
		
		TeamSaveDTO dto = TeamSaveDTO.builder()
									   .createdDt(entity.getCreatedDt())
									   .createdBy(entity.getCreatedBy().getLoggedUser())
									   .modifiedDt(entity.getModifiedDt())
									   .modifiedBy(entity.getModifiedBy().getLoggedUser())
									   .teamId(entity.getTeamId())
									   .teamName(entity.getTeamName())
									   .memberList(entity.getMembers().stream()
																	  .map(r -> r.getId().getUserId())
																	  .toList())																	  
									   .build();		
		return dto;
	}
}