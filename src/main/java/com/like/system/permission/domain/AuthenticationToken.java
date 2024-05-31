package com.like.system.permission.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.like.system.menu.dto.MenuGroupSaveDTO;
import com.like.system.user.external.SystemUserDTO;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthenticationToken implements Serializable {
		
	private static final long serialVersionUID = 7987811233360490990L;
	
	private String companyCode;
	private String userId;
	private String userName;	
	private String staffNo;
	private String email;
	private String imageUrl;
	private String ipAddress;
	private String sessionId;
	private String oAuthAccessToken;
	private List<String> roleList;
    private List<MenuGroupSaveDTO> menuGroupList;
    
       
    @Builder
    public AuthenticationToken(String companyCode
    						  ,String userId
    						  ,String userName    						  
    						  ,String staffNo 
    						  ,String email
    						  ,String imageUrl
    						  ,String ipAddress
    						  ,String sessionId
    						  ,String oAuthAccessToken
    						  ,List<String> roleList
    						  ,List<MenuGroupSaveDTO> menuGroupList) {
    	
    	this.companyCode = companyCode;
    	this.userId = userId;
    	this.userName = userName;    	
    	this.staffNo = staffNo;        
    	this.email = email;
    	this.imageUrl = imageUrl;
    	this.ipAddress = ipAddress;
        this.sessionId = sessionId;
        this.oAuthAccessToken = oAuthAccessToken;
        this.roleList = roleList;
        this.menuGroupList = menuGroupList;        
    }     
    
    public static AuthenticationToken of(SystemUserDTO user, List<MenuGroupSaveDTO> menuGroupList, String ipAddress, String sessionId) {    	
    	    	
    	return AuthenticationToken
				.builder()
				.companyCode(user.companyCode())
				.userId(user.userId())
				.userName(user.name())				
				.staffNo(user.staffNo())
				.email(user.email())
				.imageUrl(user.imageBase64())
				.ipAddress(ipAddress)
				.sessionId(sessionId)
				.roleList(user.roleList())
				//.menuGroupList(menuGroupList.stream().map(e -> new HtmlSelectOptionRecord(e.menuGroupName(), e.menuGroupCode())).toList())				
				.menuGroupList(menuGroupList)
				.build();
    }
       
    public static void main(String[] vd) {
    	BCryptPasswordEncoder b = new BCryptPasswordEncoder();
    	System.out.println(b.encode("1234"));    	
    }
}
