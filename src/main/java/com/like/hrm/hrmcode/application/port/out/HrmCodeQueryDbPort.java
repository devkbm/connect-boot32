package com.like.hrm.hrmcode.application.port.out;

import java.util.List;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeQueryDTO;
import com.like.hrm.hrmcode.domain.HrmCode;

public interface HrmCodeQueryDbPort {
	List<HrmCode> select(HrmCodeQueryDTO dto);
}
