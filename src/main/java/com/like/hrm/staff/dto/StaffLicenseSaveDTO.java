package com.like.hrm.staff.dto;

import java.time.LocalDate;

import com.like.hrm.staff.domain.model.Staff;
import com.like.hrm.staff.domain.model.license.StaffLicense;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record StaffLicenseSaveDTO(
		String clientAppUrl,
		String companyCode,			
		@NotEmpty String staffNo,
		String staffName,
		Long seq,
		@NotEmpty String licenseType,
		@NotEmpty String licenseNumber,
		LocalDate dateOfAcquisition,
		String certificationAuthority,
		String comment
		) {
	
	public StaffLicense newEntity(Staff staff) {
		StaffLicense entity = StaffLicense.builder()
										  .staff(staff)
										  .licenseType(licenseType)
										  .licenseNumber(licenseNumber)
										  .dateOfAcquisition(dateOfAcquisition)
										  .certificationAuthority(certificationAuthority)
										  .comment(comment)
										  .build();
		entity.setAppUrl(clientAppUrl);
		
		return entity; 			
	}
	
	public void modifyEntity(StaffLicense entity) {
		entity.modifyBuilder()			
		      .licenseType(licenseType)
		      .licenseNumber(licenseNumber)
		      .dateOfAcquisition(dateOfAcquisition)
		      .certificationAuthority(certificationAuthority)
		      .comment(comment)
			  .modify();
		
		entity.setAppUrl(clientAppUrl);
	}	
	
	public static StaffLicenseSaveDTO toDTO(StaffLicense entity)  {
		if (entity == null) return null; 
		
		return StaffLicenseSaveDTO.builder()
				   .companyCode(entity.getStaff().getId().getCompanyCode())
				   .staffNo(entity.getStaff().getId().getStaffNo())
				   .staffName(entity.getStaff().getName().getName())
				   .seq(entity.getId().getSeq())
				   .licenseType(entity.getLicenseType())
				   .licenseNumber(entity.getLicenseNumber())
				   .dateOfAcquisition(entity.getDateOfAcquisition())
				   .certificationAuthority(entity.getCertificationAuthority())
				   .comment(entity.getComment())
				   .build();			
	}
}