package com.like.system.holiday.application.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.like.system.holiday.application.port.in.HolidaySelectUseCase;
import com.like.system.holiday.application.port.out.HolidaySelectPort;
import com.like.system.holiday.dto.HolidaySaveDTO;

@Service
public class HolidaySelectService implements HolidaySelectUseCase {

	HolidaySelectPort port;
	
	public HolidaySelectService(HolidaySelectPort port) {
		this.port = port;
	}
	
	@Override
	public HolidaySaveDTO select(String companyCode, LocalDate date) {		
		return HolidaySaveDTO.toDTO(this.port.select(companyCode, date));
	}
	
}
