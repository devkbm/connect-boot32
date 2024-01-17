package com.like.hrm.hrmcode.application.port.in;

import java.util.List;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeSaveDTO;
import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeQueryDTO;

public interface HrmCodeTypeQueryUseCase {
	List<HrmCodeSaveDTO> select(HrmCodeTypeQueryDTO dto);
}
