package com.like.hrm.hrmcode.application.service.hrmcode;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeQueryDTO;
import com.like.hrm.hrmcode.application.port.dto.HrmCodeSaveDTO;
import com.like.hrm.hrmcode.application.port.in.hrmcode.HrmCodeQueryUseCase;
import com.like.hrm.hrmcode.application.port.out.HrmCodeQueryDbPort;

@Service
public class HrmCodeQueryService implements HrmCodeQueryUseCase {

	HrmCodeQueryDbPort dbPort;
		
	HrmCodeQueryService(HrmCodeQueryDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public List<HrmCodeSaveDTO> select(HrmCodeQueryDTO dto) {
		return dbPort.select(dto)
					 .stream()
					 .map(e -> HrmCodeSaveDTO.toDTO(e))
					 .toList();
	}

	@Override
	public Map<String, List<HrmCodeSaveDTO>> selectCodeList(List<String> typeIds) {
		List<HrmCodeSaveDTO> list = this.dbPort.selectList(typeIds)
											   .stream()
											   .map(e -> HrmCodeSaveDTO.toDTO(e))
											   .toList();
											   
		return list.stream()
				   .collect(Collectors.groupingBy(HrmCodeSaveDTO::typeId));	
	}

}
