package com.like.cooperation.board.util;

import java.util.Base64;

import org.springframework.util.StringUtils;

public class Base64Util {

	public static Long fromBase64Decode(String str) {
    	return StringUtils.hasText(str) ? Long.parseLong(new String(Base64.getDecoder().decode(str))) : null;
    }
	
	public static String toBase64Encode(Long id) {
		return Base64.getEncoder().encodeToString(id.toString().getBytes());
	}
}
