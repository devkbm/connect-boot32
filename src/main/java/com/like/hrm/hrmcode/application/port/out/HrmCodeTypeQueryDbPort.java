package com.like.hrm.hrmcode.application.port.out;

import java.util.List;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeSaveDTO;
import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeQueryDTO;

public interface HrmCodeTypeQueryDbPort {
	List<HrmCodeSaveDTO> select(HrmCodeTypeQueryDTO dto);
}
