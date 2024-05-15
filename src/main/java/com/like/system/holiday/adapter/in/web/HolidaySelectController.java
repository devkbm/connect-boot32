package com.like.system.holiday.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toOne;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.system.holiday.application.port.dto.HolidaySaveDTO;
import com.like.system.holiday.application.port.in.HolidaySelectUseCase;

@RestController
public class HolidaySelectController {

	private HolidaySelectUseCase useCase;			
	
	public HolidaySelectController(HolidaySelectUseCase useCase) {
		this.useCase = useCase;			
	}		
	
	@GetMapping("/api/system/holiday/{date}")
	public ResponseEntity<?> getHoliday(@RequestParam String companyCode,
			                            @PathVariable @DateTimeFormat(pattern="yyyyMMdd") LocalDate date) {
		
		/*
		HolidayDTO.Form dto = HolidayDTO.Form.convert(holidayService.getHoliyday(companyCode,date)
																	.orElse(new Holiday(new HolidayId(companyCode, date), "", "")));
		*/
		HolidaySaveDTO dto = this.useCase.select(companyCode, date);
		
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}		
}
