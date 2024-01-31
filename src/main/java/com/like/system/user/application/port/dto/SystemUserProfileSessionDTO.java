package com.like.system.user.application.port.dto;

import java.util.Date;

public record SystemUserProfileSessionDTO(
		String ipAddress,
		Date lastAccessedTime) {

}
