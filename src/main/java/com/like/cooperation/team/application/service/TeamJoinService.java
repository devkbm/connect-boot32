package com.like.cooperation.team.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.team.application.port.in.TeamJoinUseCase;
import com.like.cooperation.team.application.port.out.TeamCommandDbPort;
import com.like.cooperation.team.domain.Team;
import com.like.system.user.export.SystemUserDTOSelectUseCase;

@Transactional
@Service
public class TeamJoinService implements TeamJoinUseCase {

	TeamCommandDbPort dbPort;
	SystemUserDTOSelectUseCase userSelectUseCase;
	
	TeamJoinService(TeamCommandDbPort dbPort,
			SystemUserDTOSelectUseCase userSelectUseCase) {
		this.dbPort = dbPort;
		this.userSelectUseCase = userSelectUseCase;
	}
	
	@Override
	public void join(Long teamId, String companyCode, String userId) {
		Team team = dbPort.select(teamId);				
		
		team.addMember(userId);	
		
		dbPort.save(team);
	}

}
