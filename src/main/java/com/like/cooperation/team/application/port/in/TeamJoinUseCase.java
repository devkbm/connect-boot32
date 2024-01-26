package com.like.cooperation.team.application.port.in;

public interface TeamJoinUseCase {
	void join(Long teamId, String companyCode, String userId);
}
