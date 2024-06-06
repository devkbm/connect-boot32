package com.like.system.file.external;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadUseCase {

	FileInfoDTO uploadFile(MultipartFile sourceFile, String userId, String appUrl);
	
	List<FileInfoDTO> uploadFile(List<MultipartFile> sourceFiles, String userId, String appUrl);
}
