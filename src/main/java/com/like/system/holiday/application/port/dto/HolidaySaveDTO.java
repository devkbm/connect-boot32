package com.like.system.holiday.application.port.dto;

import java.time.LocalDate;

import com.like.system.holiday.domain.Holiday;
import com.like.system.holiday.domain.HolidayId;

import lombok.Builder;

@Builder
public record HolidaySaveDTO(
		String companyCode,
		String clientAppUrl,
		LocalDate date,
		String holidayName,			
		String comment
		) {
	
	public Holiday newEntity() {	
		
		Holiday entity = new Holiday(new HolidayId(companyCode, date), holidayName, comment);
		
		entity.setAppUrl(clientAppUrl);
		
		return entity;
	}
	
	public void modifyEntity(Holiday entity) {
		entity.modify(holidayName, comment);
	}
	
	public static HolidaySaveDTO toDTO(Holiday entity) {
		if (entity == null) return null;
		
		return HolidaySaveDTO
					.builder()
					.companyCode(entity.getId().getCompanyCode())
					.date(entity.getId().getDate())
					.holidayName(entity.getHolidayName())
					.comment(entity.getComment())
					.build(); 
	}
}
