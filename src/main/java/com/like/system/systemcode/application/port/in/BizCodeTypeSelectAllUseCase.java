package com.like.system.systemcode.application.port.in;

import java.util.List;

import com.like.system.systemcode.application.port.dto.BizCodeTypeSaveDTO;

public interface BizCodeTypeSelectAllUseCase {

	List<BizCodeTypeSaveDTO> select(String companyCode);
}
