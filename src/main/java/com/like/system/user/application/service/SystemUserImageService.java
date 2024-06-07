package com.like.system.user.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.like.system.file.external.FileDownloadUseCase;
import com.like.system.user.application.port.in.SystemUserImageChangeUseCase;
import com.like.system.user.application.port.in.SystemUserImageFileUseCase;
import com.like.system.user.application.port.out.SystemUserCommandDbPort;
import com.like.system.user.domain.ProfilePictureRepository;
import com.like.system.user.domain.SystemUser;

import jakarta.servlet.http.HttpServletResponse;

@Transactional
@Service
public class SystemUserImageService implements SystemUserImageFileUseCase, SystemUserImageChangeUseCase {

	SystemUserCommandDbPort port;	
	ProfilePictureRepository profilePictureRepository;
	FileDownloadUseCase fileDownLoadUseCase;
		
	SystemUserImageService(SystemUserCommandDbPort port						  
						  ,ProfilePictureRepository profilePictureRepository
						  ,FileDownloadUseCase fileDownLoadUseCase) {
		this.port = port;	
		this.profilePictureRepository = profilePictureRepository;
		this.fileDownLoadUseCase = fileDownLoadUseCase;
	}
	
	@Override
	public HttpServletResponse downloadImageFile(String companyCode, String userId, HttpServletResponse response) throws Exception {
		SystemUser user = this.port.select(companyCode, userId);
				
		fileDownLoadUseCase.download(user.getImage(), response);
		
		return response;
	}

	@Override
	public String changeImage(String companyCode, String userId, MultipartFile file) {
		SystemUser user = this.port.select(companyCode, userId);
		
		if (user == null) return null;
		
		String path = user.changeImage(profilePictureRepository, file);
		
		this.port.save(user);
		
		return path;
	}	

}
