package com.like.hrm.staff.application.port.in;

import org.springframework.web.multipart.MultipartFile;

public interface StaffImageUploadUseCase {
	String upload(String companyCode, String staffNo, MultipartFile file);
}
