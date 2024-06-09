package com.like.cooperation.workcalendar.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.cooperation.workcalendar.application.port.in.WorkCalendarSaveUseCase;
import com.like.cooperation.workcalendar.application.port.out.WorkCalendarCommandDbPort;
import com.like.cooperation.workcalendar.application.port.out.WorkCalendarMemberCommandDbPort;
import com.like.cooperation.workcalendar.domain.WorkCalendar;
import com.like.cooperation.workcalendar.domain.WorkCalendarMember;
import com.like.cooperation.workcalendar.dto.WorkCalendarSaveDTO;
import com.like.system.user.export.SystemUserDTOSelectUseCase;

@Transactional
@Service
public class WorkCalendarSaveService implements WorkCalendarSaveUseCase {

	WorkCalendarCommandDbPort dbPort;
	WorkCalendarMemberCommandDbPort memberDbPort;
	SystemUserDTOSelectUseCase userSelectUseCase;
	
	WorkCalendarSaveService(WorkCalendarCommandDbPort dbPort,
							WorkCalendarMemberCommandDbPort memberDbPort,
							SystemUserDTOSelectUseCase userSelectUseCase) {
		this.dbPort = dbPort;
		this.memberDbPort = memberDbPort;
		this.userSelectUseCase = userSelectUseCase;
	}
	
	@Override
	public void save(WorkCalendarSaveDTO dto) {
		WorkCalendar entity = null;
		
		if (dto.workCalendarId() != null) {
			entity = dbPort.select(dto.workCalendarId());
		}
		
		if (entity == null) {
			entity = dto.newWorkGroup();
		} else {
			dto.modifyWorkGroup(entity);
		}
		
		dbPort.save(entity);
		
		memberDbPort.delete(entity.getMemberList().stream().toList());
										
		if (dto.memberList() != null) {
			WorkCalendar workCalendar = entity;			
			
			List<WorkCalendarMember> memberList = userSelectUseCase.findUsers(dto.companyCode(), dto.memberList())
																   .stream()
																   .map(e -> new WorkCalendarMember(workCalendar, e.userId()))
																   .toList();
			
			memberDbPort.save(memberList);									
		}	
				
		
	}

}
