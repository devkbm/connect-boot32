package com.like.system.term.application.port.in;

import java.util.List;

import com.like.system.term.dto.DataDomainSaveDTO;

public interface DataDomainQueryUseCase {
	List<DataDomainSaveDTO> selectList();
}
