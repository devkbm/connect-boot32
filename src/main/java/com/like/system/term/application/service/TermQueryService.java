package com.like.system.term.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.term.application.port.in.TermQueryUseCase;
import com.like.system.term.application.port.out.TermQueryDbPort;
import com.like.system.term.dto.TermQueryDTO;
import com.like.system.term.dto.TermSaveDTO;


@Transactional(readOnly = true)
@Service
public class TermQueryService implements TermQueryUseCase {

	TermQueryDbPort dbPort;

	public TermQueryService(TermQueryDbPort dbPort) {
		this.dbPort = dbPort;
	}	

	@Override
	public List<TermSaveDTO> select(TermQueryDTO dto) {
		return this.dbPort.select(dto)
						  .stream()
						  .map(e -> TermSaveDTO.toDTO(e))
						  .toList();
	}
}
