package com.skt.apollo.filter;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.skt.apollo.models.common.ErrorCode;
import com.skt.apollo.util.URLUtil;

public class CSRFFilter extends OncePerRequestFilter implements Filter  {
	
	private static final Logger log = LoggerFactory.getLogger(com.skt.apollo.filter.CSRFFilter.class);
	
	private String includePattern = StringUtils.EMPTY;
	private String excludePattern = StringUtils.EMPTY;
	
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
	
	/**
	 * Check URI. If it need prevent CSRF attack then return true. Else return false.
	 *  
	 * @param request
	 * @return
	 */
	private boolean isFormUrl(String uri) {
		
		if ( uri.indexOf("/addform") > -1
	       	|| uri.indexOf("/modform") > -1
	       	|| uri.indexOf("/saveform") > -1
	       	|| uri.indexOf("/form") > -1
	       	|| uri.indexOf("/cadm/member/user/list") > -1
	       	|| uri.indexOf("/cadm/threat/major/list") > -1
	       	|| uri.indexOf("/cadm/threat/similar/list") > -1
	       	|| uri.indexOf("/cadm/threat/association/list") > -1
	       	|| uri.indexOf("/cadm/threat/detect/list") > -1
	       	|| uri.indexOf("/cadm/share/user/")> -1
	       	|| uri.indexOf("/cadm/manager/pw/list")> -1
        ) {
        	return true;
        }
		
		return false;
	}
	
	private boolean isProcessUrl(String uri) {
		
		if ( uri.indexOf("/addprocess") > -1
	       	|| uri.indexOf("/modprocess") > -1
	       	|| uri.indexOf("/save") > -1
        ) {
        	return true;
        }
		
		return false;
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
		
		log.debug("########## CSRFFilter request uri : " + uri + " ##########");
		log.debug("########## CSRFFilter includePattern : " + getIncludePattern() + " ##########");
		log.debug("########## CSRFFilter excludePattern : " + getExcludePattern() + " ##########");
		
		// 검사대상 URL 패턴 확인
		if ((StringUtils.isNotEmpty(getIncludePattern()) ? isIncludePattern(uri, getIncludePatternArr()) : true) &&
				(StringUtils.isNotEmpty(getExcludePattern()) ? !isIncludePattern(uri, getExcludePatternArr()) : true)) {
        
	        if ( isFormUrl(uri) ) {
	 
		        // Check the user session for the salt cache, if none is present we create one
		        Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>)
		        		request.getSession().getAttribute("csrfPreventionSaltCache");
		 
		        if (csrfPreventionSaltCache == null){
		            csrfPreventionSaltCache = CacheBuilder.newBuilder()
		                .maximumSize(5000)
		                .expireAfterWrite(20, TimeUnit.MINUTES)
		                .build();
		 
		            request.getSession().setAttribute("csrfPreventionSaltCache", csrfPreventionSaltCache);
		        }
		 
		        // Generate the salt and store it in the users cache
		        String salt = RandomStringUtils.random(20, 0, 0, true, true, null, new SecureRandom());
		        csrfPreventionSaltCache.put(salt, Boolean.TRUE);
		 
		        // Add the salt to the current request so it can be used
		        // by the page rendered in this request
		        request.setAttribute("csrfPreventionSalt", salt);
	        }
	        else if ( isProcessUrl(uri) ) {
	        	
	        	// Get the salt sent with the request
	            String salt = (String) request.getParameter("csrfPreventionSalt");
	            
	            // Validate that the salt is in the cache
	            Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>)
	            		request.getSession().getAttribute("csrfPreventionSaltCache");
	     
	            if (csrfPreventionSaltCache != null 
	            		&& salt != null 
	            		&& csrfPreventionSaltCache.getIfPresent(salt) != null ){
	            } else {
	                // Otherwise we throw an exception aborting the request flow
	                throw ErrorCode._400._4005.asException();
	            }
	        }
		}
        
        chain.doFilter(request, response);
	}
	

}
