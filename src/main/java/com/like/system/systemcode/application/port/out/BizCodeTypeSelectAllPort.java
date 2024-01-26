package com.like.system.systemcode.application.port.out;

import java.util.List;

import com.like.system.systemcode.application.port.dto.BizCodeTypeSaveDTO;

public interface BizCodeTypeSelectAllPort {
	List<BizCodeTypeSaveDTO> select(String companyCode);
}
