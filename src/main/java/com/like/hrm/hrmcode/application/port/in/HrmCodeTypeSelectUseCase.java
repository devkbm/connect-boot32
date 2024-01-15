package com.like.hrm.hrmcode.application.port.in;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeSaveDTO;

public interface HrmCodeTypeSelectUseCase {

	HrmCodeTypeSaveDTO select(String id);
}
