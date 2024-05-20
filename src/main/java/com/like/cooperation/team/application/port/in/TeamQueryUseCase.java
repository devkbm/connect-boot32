package com.like.cooperation.team.application.port.in;

import java.util.List;

import com.like.cooperation.team.domain.Team;
import com.like.cooperation.team.domain.TeamMember;
import com.like.cooperation.team.dto.TeamQueryDTO;
import com.like.system.user.dto.SystemUserQueryDTO;
import com.like.system.user.dto.SystemUserSaveDTO;

public interface TeamQueryUseCase {
	List<Team> selectTeamList(TeamQueryDTO dto);
	
	List<TeamMember> selectTeamMemeberList(Long id);
	
	List<SystemUserSaveDTO> selectAllMemberList(SystemUserQueryDTO dto);
}
