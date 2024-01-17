package com.like.hrm.hrmcode.application.port.out;

import java.util.List;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeQueryDTO;
import com.like.hrm.hrmcode.domain.HrmCodeType;

public interface HrmCodeTypeQueryDbPort {
	List<HrmCodeType> select(HrmCodeTypeQueryDTO dto);
}
