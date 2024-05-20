package com.like.system.user.dto;

import java.util.Date;

public record SystemUserProfileSessionDTO(
		String ipAddress,
		Date lastAccessedTime) {

}
