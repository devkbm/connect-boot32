package com.like.hrm.staff.dto;

import java.time.LocalDate;

import com.like.hrm.staff.domain.model.Staff;

public record ResponseStaff(			
		String companyCode,
		String staffNo,
		String name,
		String nameEng,
		String nameChi,
		String residentRegistrationNumber,
		String gender,
		LocalDate birthday,
		String imagePath
		) {
			
	public static ResponseStaff toDTO(Staff entity) {
		
		if (entity == null) return null;			
		
		var name = entity.getName();
		
		return new ResponseStaff(entity.getId().getCompanyCode()
								,entity.getId().getStaffNo()
							   	,name.getName()
							   	,name.getNameEng()
							   	,name.getNameChi()
							   	,entity.getResidentRegistrationNumber().getNumber()
							   	,entity.getGender()
							   	,entity.getBirthday()
							   	,entity.getImagePath());								   
							   
	}
}