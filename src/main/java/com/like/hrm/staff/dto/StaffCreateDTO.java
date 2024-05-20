package com.like.hrm.staff.dto;

import jakarta.validation.constraints.NotEmpty;

public record StaffCreateDTO(
		String clientAppUrl,
		String companyCode,			
		@NotEmpty(message = "직원번호는 필수 입력 값입니다.")
		String staffNo,
		@NotEmpty(message = "이름은 필수 입력 값입니다.")
		String name,
		@NotEmpty(message = "주민등록번호는 필수 입력 값입니다.")
		String residentRegistrationNumber,
		String nameEng,
		String nameChi			
		) {	
	
	public String getStaffId() {
		return this.companyCode + "_" + this.staffNo;
	}
	
}