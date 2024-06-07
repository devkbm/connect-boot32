package com.like.system.file.adapter.in.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.like.system.file.adapter.out.file.FileConverterUtil;
import com.like.system.file.application.port.in.FileServerDownloadUseCase;
import com.like.system.file.dto.FileDTO;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FileServerDownloadController {

	FileServerDownloadUseCase useCase;
	
	FileServerDownloadController(FileServerDownloadUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/system/file/{id}")
	public HttpServletResponse fileDownLoad(HttpServletResponse response
										   ,@PathVariable String id) throws Exception {														
		FileDTO fileDTO = useCase.getDownloadFile(id);		
		
		try (OutputStream os = response.getOutputStream()) {			
			FileConverterUtil.fileToStream(fileDTO.file(), os);			
		} catch (IOException e1) {		
			e1.printStackTrace();
		}
		
		setResponse(response, fileDTO.size(), fileDTO.fileName(), "application/octet-stream");
		
		return response;
	}
	
	@GetMapping("/api/system/fileimage/{id}")
	public HttpServletResponse fileImageDownLoad(HttpServletResponse response
												,@PathVariable String id) throws Exception {
		
		FileDTO fileDTO = useCase.getDownloadFile(id);		
		
		try (OutputStream os = response.getOutputStream()) {			
			FileConverterUtil.fileToStream(fileDTO.file(), os);			
		} catch (IOException e1) {		
			e1.printStackTrace();
		}
		
		setImageResponse(response, fileDTO.size(), fileDTO.mimeType());
		
		return response;
	}	
	
	private void setResponse(HttpServletResponse response, long fileSize, String fileName, String mimeType) {
		// get MIME type of the file		
		if (StringUtils.hasText(mimeType)) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";	         
		}
		
		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLengthLong(fileSize);
		response.setCharacterEncoding("UTF-8");
		
		// set headers for the response
		String headerFileName = "";
		try {
			headerFileName = new String(fileName.getBytes("EUC-KR"), "8859_1");			
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}				
		
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment;filename=\"%s\"", headerFileName);
		
		response.setHeader(headerKey, headerValue);
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");		
	}
	
	private void setImageResponse(HttpServletResponse response, long fileSize, String contentType) {
		response.setContentType(contentType);
		response.setContentLengthLong(fileSize);
		response.setCharacterEncoding("UTF-8");
	}
}
