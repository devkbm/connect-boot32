package com.like.hrm.hrmcode.application.port.in.hrmcodetype;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeSaveDTO;

public interface HrmCodeTypeSelectUseCase {

	HrmCodeTypeSaveDTO select(String id);
}
