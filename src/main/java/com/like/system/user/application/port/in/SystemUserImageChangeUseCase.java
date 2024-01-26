package com.like.system.user.application.port.in;

import org.springframework.web.multipart.MultipartFile;

public interface SystemUserImageChangeUseCase {
	
	String changeImage(String companyCode, String userId, MultipartFile file);
}
