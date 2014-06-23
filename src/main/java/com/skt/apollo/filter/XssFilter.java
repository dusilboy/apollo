package com.skt.apollo.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.skt.apollo.filter.html.HTMLInputFilter;
import com.skt.apollo.util.HttpRequestWithModifiableParameters;
import com.skt.apollo.util.URLUtil;

public class XssFilter extends OncePerRequestFilter implements Filter  {
	
	private static final Logger log = LoggerFactory.getLogger(com.skt.apollo.filter.XssFilter.class);
	
	private String includePattern = StringUtils.EMPTY;
	private String excludePattern = StringUtils.EMPTY;
	private HTMLInputFilter xssFilter;
	
	public void setXssFilter(HTMLInputFilter xssFilter) {
		this.xssFilter = xssFilter;
	}

	public HTMLInputFilter getXssFilter() {
		return xssFilter;
	}
	
	public void setIncludePattern(String superUserCode) {
		this.includePattern = superUserCode;
	}

	public String getIncludePattern() {
		return includePattern;
	}
	
	public String[] getIncludePatternArr() {
		if (StringUtils.isNotEmpty(includePattern)) {
			return includePattern.split(",");
		}
		return null;
	}
	
	public void setExcludePattern(String superUserCode) {
		this.excludePattern = superUserCode;
	}

	public String getExcludePattern() {
		return excludePattern;
	}
	
	public String[] getExcludePatternArr() {
		if (StringUtils.isNotEmpty(excludePattern)) {
			return excludePattern.split(",");
		}
		return null;
	}
	
	private boolean isIncludePattern(String uri, String[] patternArr) {
		if (StringUtils.isNotEmpty(uri)) {
			for (int i=0; i<patternArr.length; ++i) {
				if (StringUtils.isNotEmpty(patternArr[i]) && StringUtils.containsIgnoreCase(uri, patternArr[i])) {
					log.debug("@@@@@@@@@@ patternArr[" + i + "] = " + patternArr[i] + " @@@@@@@@@@");
					return true;
				}
			}
		}
		return false;
	}
	
	public int getOrder() {
		
		return 2;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		
		// exclude url
		if ( URLUtil.isExcludeFilterURI(uri)) {
        	chain.doFilter(request, response);
        	return ;
        }
		
		HttpRequestWithModifiableParameters httpRequest = new HttpRequestWithModifiableParameters(request);
		
		log.debug("########## XssFilter request uri : " + uri + " ##########");
		log.debug("########## XssFilter includePattern : " + getIncludePattern() + " ##########");
		log.debug("########## XssFilter excludePattern : " + getExcludePattern() + " ##########");
		
		if ((StringUtils.isNotEmpty(getIncludePattern()) ? isIncludePattern(uri, getIncludePatternArr()) : true) &&
				(StringUtils.isNotEmpty(getExcludePattern()) ? !isIncludePattern(uri, getExcludePatternArr()) : true)) {
		
			if (getXssFilter() != null) {
				Map paramMap = httpRequest.getParameterMap();
				Enumeration enumeration = request.getParameterNames();
				while (enumeration.hasMoreElements()) {
					String paramName = (String) enumeration.nextElement();
					String[] params = (String[]) paramMap.get(paramName);
					
					log.debug("##### parameterName === " + paramName);
					
					if (params != null && params.length > 0) {
						for (int i=0; i<params.length; ++i) {
							if (StringUtils.isNotEmpty(params[i])) {
								log.debug("$$$$$ before xssFilter === " + params[i]);
								params[i] = xssFilter.filter(params[i]);
								log.debug("$$$$$ after xssFilter === " + params[i]);
							}
						}
					}
					
					httpRequest.setParameter(paramName, params);
				}
			}
		}
		
		chain.doFilter(httpRequest, response);
	}

}
