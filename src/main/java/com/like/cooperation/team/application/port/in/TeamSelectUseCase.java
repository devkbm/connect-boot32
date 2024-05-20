package com.like.cooperation.team.application.port.in;

import com.like.cooperation.team.dto.TeamSaveDTO;

public interface TeamSelectUseCase {
	TeamSaveDTO select(Long teamId);
}
