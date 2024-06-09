package com.like.system.user.adapter.out.persistence.file;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.like.core.util.SessionUtil;
import com.like.system.file.export.FileDeleteUseCase;
import com.like.system.file.export.FileInfoDTO;
import com.like.system.file.export.FileUploadUseCase;
import com.like.system.user.domain.ProfilePictureRepository;

@Repository
public class StaffProfilePictureRepository implements ProfilePictureRepository {

	FileUploadUseCase uploadUseCase;
	FileDeleteUseCase deleteUseCase;
	
	public StaffProfilePictureRepository(FileUploadUseCase uploadUseCase
										,FileDeleteUseCase deleteUseCase) {
		this.uploadUseCase = uploadUseCase;
		this.deleteUseCase = deleteUseCase;
	}
	
	@Override
	public String upload(MultipartFile sourceFile) {
		FileInfoDTO fileInfo = uploadUseCase.uploadFile(sourceFile, SessionUtil.getUserId(), "SystemUser");
		return fileInfo.fildId().toString();		
	}

	@Override
	public void delete(String path) {		
		deleteUseCase.delete(path);				
	}

}
