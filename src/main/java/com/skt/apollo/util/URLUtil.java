package com.skt.apollo.util;



public class URLUtil {
	
	public static boolean isExcludeFilterURI(String uri) {
		
		if(uri.matches(".*(css|jpg|png|gif|js|swf)")){
		    return true;
		}
		return false;
	}
}
