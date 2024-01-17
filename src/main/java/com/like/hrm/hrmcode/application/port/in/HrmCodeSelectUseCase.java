package com.like.hrm.hrmcode.application.port.in;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeSaveDTO;

public interface HrmCodeSelectUseCase {
	HrmCodeSaveDTO select(String type, String code);
}
