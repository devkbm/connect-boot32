package com.like.system.file.application.service.external;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.like.system.file.adapter.out.file.FileServerRepository;
import com.like.system.file.application.port.out.FileInfoCommandDbPort;
import com.like.system.file.domain.FileInfo;
import com.like.system.file.external.FileInfoDTO;
import com.like.system.file.external.FileUploadUseCase;

@Service
public class ExternalFileUploadService implements FileUploadUseCase {

	FileInfoCommandDbPort dbPort;
	FileServerRepository fileServerRepository;
	
	ExternalFileUploadService(FileInfoCommandDbPort dbPort
		 	   				 ,FileServerRepository fileServerRepository) {
		
	}
	
	@Override
	public FileInfoDTO uploadFile(MultipartFile sourceFile, String userId, String appUrl) {
		FileInfo entity = FileInfo.create(sourceFile, fileServerRepository.getFileServerUploadPath(), userId, appUrl);		
		
		toFileServer(sourceFile, entity.getUuid());
		
		return FileInfoDTO.toDTO(this.dbPort.save(entity));
	}

	@Override
	public List<FileInfoDTO> uploadFile(List<MultipartFile> sourceFiles, String userId, String appUrl) {
		List<FileInfo> files = new ArrayList<>(sourceFiles.size());
		
		for (MultipartFile file : sourceFiles) {			
			FileInfo entity = FileInfo.create(file, fileServerRepository.getFileServerUploadPath(), userId, appUrl);
			
			toFileServer(file, entity.getUuid());
			
			files.add(entity);
		}				    
		
		return this.dbPort.save(files)
						  .stream()
						  .map(e -> FileInfoDTO.toDTO(e))
						  .toList();
	}
	
	private void toFileServer(MultipartFile sourceFile, String serverFileName) {
		try {			
			fileServerRepository.fileTransfer(sourceFile, serverFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
