package com.like.system.holiday.application.service;

import org.springframework.stereotype.Service;

import com.like.system.holiday.application.port.in.HolidaySaveUseCase;
import com.like.system.holiday.application.port.out.HolidaySavePort;
import com.like.system.holiday.dto.HolidaySaveDTO;

@Service
public class HolidaySaveService implements HolidaySaveUseCase {

	HolidaySavePort port;
	
	public HolidaySaveService(HolidaySavePort port) {
		this.port = port;
	}
	
	@Override
	public void save(HolidaySaveDTO dto) {	
		this.port.save(dto.newEntity());
	}

}
