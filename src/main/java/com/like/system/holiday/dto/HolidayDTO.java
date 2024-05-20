package com.like.system.holiday.dto;

import java.time.LocalDate;

import com.like.system.holiday.domain.Holiday;
import com.like.system.holiday.domain.HolidayId;

import lombok.AccessLevel;
import lombok.Builder;

public class HolidayDTO {

	@Builder(access = AccessLevel.PRIVATE)
	public static record Form(
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
		
		public static Form convert(Holiday entity) {
			return Form.builder()
					   .companyCode(entity.getId().getCompanyCode())
					   .date(entity.getId().getDate())
					   .holidayName(entity.getHolidayName())
					   .comment(entity.getComment())
					   .build(); 
		}
	}
}
