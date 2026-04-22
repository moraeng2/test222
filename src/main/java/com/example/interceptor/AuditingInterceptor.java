package com.example.interceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuditingInterceptor implements HandlerInterceptor {
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	private String user;
	private String bookId;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getRequestURI().endsWith("books/add") && request.getMethod().equals("POST")) {
			user = request.getRemoteUser();
			bookId = request.getParameterValues("bookId")[0];
		}

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(request.getRequestURI().endsWith("books/add")) {
		logger.warn(String.format("신규등록 도서 ID: %s, 접근자 : %s, 접근시각: %s", bookId, user, getCurrentTime()));
		}
	}
	
//	private String getURLPath(HttpServletRequest request) {
//		String currentPath = request.getRequestURI();
//		String queryString = request.getQueryString();
//		queryString = queryString == null ? "" : "?" + queryString;
//		return currentPath + queryString;
//	}
	
	private String getCurrentTime() {
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return formatter.format(calendar.getTime());
	}

}
