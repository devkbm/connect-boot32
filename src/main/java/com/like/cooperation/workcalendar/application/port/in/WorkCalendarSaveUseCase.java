package com.like.cooperation.workcalendar.application.port.in;

import com.like.cooperation.workcalendar.dto.WorkCalendarSaveDTO;

public interface WorkCalendarSaveUseCase {
	void save(WorkCalendarSaveDTO dto);
}
