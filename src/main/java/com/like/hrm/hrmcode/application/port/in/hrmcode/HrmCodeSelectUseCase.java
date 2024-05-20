package com.like.hrm.hrmcode.application.port.in.hrmcode;

import com.like.hrm.hrmcode.dto.HrmCodeSaveDTO;

public interface HrmCodeSelectUseCase {
	HrmCodeSaveDTO select(String type, String code);
}
