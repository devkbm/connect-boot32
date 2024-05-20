package com.like.cooperation.team.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.cooperation.team.domain.Team;
import com.like.cooperation.team.domain.TeamMember;
import com.like.cooperation.team.dto.TeamQueryDTO;

@Repository
public interface TeamQueryRepository {

	List<Team> getTeamList(TeamQueryDTO searchCondition);	
	
	List<TeamMember> getTeamMemberList(Long teamId);
}
