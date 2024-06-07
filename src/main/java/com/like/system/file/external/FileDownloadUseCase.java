package com.like.system.file.external;

import jakarta.servlet.http.HttpServletResponse;

public interface FileDownloadUseCase {

	void download(String fileInfoId, HttpServletResponse response);	
	
	void viewImage(String fileInfoId, HttpServletResponse response);
}
