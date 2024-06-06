package com.like.system.file.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.file.application.port.out.FileInfoCommandDbPort;
import com.like.system.file.external.FileInfoDTO;
import com.like.system.file.external.FileInfoDTOSelectUseCase;

@Transactional(readOnly = true)
@Service
public class FileInfoDTOSelectService implements FileInfoDTOSelectUseCase {

	FileInfoCommandDbPort dbPort;
	
	FileInfoDTOSelectService(FileInfoCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public FileInfoDTO select(String id) {
		return FileInfoDTO.toDTO(this.dbPort.getFileInfo(id));
	}

	@Override
	public List<FileInfoDTO> select(List<String> ids) {
		return this.dbPort.getFileInfo(ids)
				  		  .stream()
				  		  .map(e -> FileInfoDTO.toDTO(e))
				  		  .toList();
	}

}
