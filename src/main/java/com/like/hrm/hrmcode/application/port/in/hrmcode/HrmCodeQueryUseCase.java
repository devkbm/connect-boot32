package com.like.hrm.hrmcode.application.port.in.hrmcode;

import java.util.List;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeQueryDTO;
import com.like.hrm.hrmcode.application.port.dto.HrmCodeSaveDTO;

public interface HrmCodeQueryUseCase {
	List<HrmCodeSaveDTO> select(HrmCodeQueryDTO dto);
}
