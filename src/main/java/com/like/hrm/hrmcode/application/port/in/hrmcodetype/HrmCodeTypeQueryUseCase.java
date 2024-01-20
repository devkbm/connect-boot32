package com.like.hrm.hrmcode.application.port.in.hrmcodetype;

import java.util.List;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeQueryDTO;
import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeSaveDTO;

public interface HrmCodeTypeQueryUseCase {
	List<HrmCodeTypeSaveDTO> select(HrmCodeTypeQueryDTO dto);
}
