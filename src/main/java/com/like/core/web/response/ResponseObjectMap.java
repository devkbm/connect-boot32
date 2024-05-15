package com.like.core.web.response;

import java.util.Map;

import lombok.Data;

@Data
public class ResponseObjectMap<K, V> {

	Map<K, V> data;
	
	int total;	
	
	String message;
	
	public ResponseObjectMap(Map<K, V> data, int total, String message) {
		this.data = data;
		this.total = total;
		this.message = message;
	}
}
