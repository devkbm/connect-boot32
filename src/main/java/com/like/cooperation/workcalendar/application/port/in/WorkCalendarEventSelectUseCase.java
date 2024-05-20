package com.like.cooperation.workcalendar.application.port.in;

import com.like.cooperation.workcalendar.dto.WorkCalendarEventResponseDTO;

public interface WorkCalendarEventSelectUseCase {
	WorkCalendarEventResponseDTO select(Long id);
}
