package com.symtoms.checker.alexa.integration.client.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.symtoms.checker.alexa.data.SymtomsRequest;
import com.symtoms.checker.alexa.integration.client.SymtomsConnectivityClient;

public class DefaultSymtomsConnectivityClient<T> implements SymtomsConnectivityClient {

	private Logger LOG = LoggerFactory.getLogger(DefaultSymtomsConnectivityClient.class);

	@Resource(name = "symtomsRestTemplate")
	private RestTemplate symtomsRestTemplate;

	private static final String PRODUCT="product";
	@Override
	public Object invokeRequest(SymtomsRequest request, Class clazz) {
		try {
			LOG.error(request.getUrl());
			HttpEntity<?> entity;
			if(null!=request.getPostData()) {
				entity = new HttpEntity(request.getPostData().get(PRODUCT),getHybrisSpecificHttpHeader(request));	
			}
			else {
			    entity = new HttpEntity(getHybrisSpecificHttpHeader(request));	
			}
			ResponseEntity<T> response = symtomsRestTemplate.exchange(request.getUrl(), request.getMethod(), entity, clazz);
			if (null != response.getStatusCode() && response.getStatusCode().equals(HttpStatus.OK)) {
				return response.getBody();
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;

	}
	private HttpHeaders getHybrisSpecificHttpHeader(SymtomsRequest request) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		Map<String, String> headers = request.getHeaders();
		for(String key : headers.keySet()) {
			requestHeaders.set(key, headers.get(key));
		}
		return requestHeaders;
	}

}
