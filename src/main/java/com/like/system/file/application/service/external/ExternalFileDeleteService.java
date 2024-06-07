package com.like.system.file.application.service.external;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.stereotype.Service;

import com.like.system.file.adapter.out.file.FileServerRepository;
import com.like.system.file.application.port.out.FileInfoCommandDbPort;
import com.like.system.file.domain.FileInfo;
import com.like.system.file.external.FileDeleteUseCase;

@Service
public class ExternalFileDeleteService implements FileDeleteUseCase {

	FileInfoCommandDbPort dbPort;
	FileServerRepository fileServerRepository;
	
	ExternalFileDeleteService(FileInfoCommandDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void delete(String fileInfoId) {
		FileInfo fileInfo = dbPort.getFileInfo(fileInfoId);
		
		try {
			fileServerRepository.deleteFile(new File(fileInfo.getPath(), fileInfo.getUuid()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.dbPort.delete(fileInfoId);
	}

}
