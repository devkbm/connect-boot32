package com.like.system.term.application.port.in;

import java.util.List;

import com.like.system.term.dto.TermQueryDTO;
import com.like.system.term.dto.TermSaveDTO;

public interface TermQueryUseCase {
	List<TermSaveDTO> select(TermQueryDTO dto);
}
