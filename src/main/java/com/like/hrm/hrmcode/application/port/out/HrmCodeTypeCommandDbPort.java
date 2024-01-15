package com.like.hrm.hrmcode.application.port.out;

import java.util.Optional;

import com.like.hrm.hrmcode.domain.HrmCodeType;

public interface HrmCodeTypeCommandDbPort {

	Optional<HrmCodeType> select(String id);
	
	void save(HrmCodeType entity);
	
	void delete(String id);
}
