package com.like.hrm.hrmcode.adapter.in.web.hrmcode;

import static com.like.core.web.util.ResponseEntityUtil.toList;
import static com.like.core.web.util.ResponseEntityUtil.toMap;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeSaveDTO;
import com.like.hrm.hrmcode.application.port.in.hrmcode.HrmCodeQueryUseCase;
import com.like.core.message.MessageUtil;
import com.like.hrm.hrmcode.application.port.dto.HrmCodeQueryDTO;

@RestController
public class HrmCodeQueryController {

	HrmCodeQueryUseCase useCase;
	
	HrmCodeQueryController(HrmCodeQueryUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/hrm/hrmtype/code")
	public ResponseEntity<?> getHrmTypeDetailCodeList(HrmCodeQueryDTO dto) {														
		
		List<HrmCodeSaveDTO> list = useCase.select(dto);																	   
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/hrmtype/codelist")
	public ResponseEntity<?> getHrmTypeList(@RequestParam List<String> typeIds) {														
		
		Map<String, List<HrmCodeSaveDTO>> list = useCase.selectCodeList(typeIds);				
								
		return toMap(list, MessageUtil.getQueryMessage(list.size()));
	}
}
