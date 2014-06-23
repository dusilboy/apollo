package com.skt.apollo.filter.sql;

import org.apache.commons.lang.StringUtils;

public class SQLInjectionFilter
{
	// SQL injection 방지
	public String removeInjection(String source) {
		
		String result = StringUtils.EMPTY;
		
		if (StringUtils.isNotEmpty(source)) {
			
			source = StringUtils.replace(source, "&lsquo;", "‘");
			source = StringUtils.replace(source, "&rsquo;", "’");
			source = StringUtils.replace(source, "'", "''");
			source = StringUtils.replace(source, ";", "");
			source = StringUtils.replace(source, "--", "");
			source = StringUtils.replace(source, "/*", "");
			source = StringUtils.replace(source, "*/", "");
			
			source = StringUtils.replace(source, "create", "");
			source = StringUtils.replace(source, "drop", "");
			source = StringUtils.replace(source, "exec", "");
			source = StringUtils.replace(source, "declare", "");
			source = StringUtils.replace(source, "select", "");
			source = StringUtils.replace(source, "insert", "");
			source = StringUtils.replace(source, "update", "");
			source = StringUtils.replace(source, "delete", "");
			source = StringUtils.replace(source, "shutdown", "");
			source = StringUtils.replace(source, "sp_", "");
			source = StringUtils.replace(source, "xp_", "");
			source = StringUtils.replace(source, "CREATE", "");
			source = StringUtils.replace(source, "DROP", "");
			source = StringUtils.replace(source, "EXEC", "");
			source = StringUtils.replace(source, "DECLARE", "");
			source = StringUtils.replace(source, "SELECT", "");
			source = StringUtils.replace(source, "INSERT", "");
			source = StringUtils.replace(source, "UPDATE", "");
			source = StringUtils.replace(source, "DELETE", "");
			source = StringUtils.replace(source, "SHUTDOWN", "");
			source = StringUtils.replace(source, "SP_", "");
			source = StringUtils.replace(source, "XP_", "");
			
			source = StringUtils.replace(source, "&gt", "");
			source = StringUtils.replace(source, "&lt", "");
			
			source = StringUtils.replace(source, "%20", "");
			source = StringUtils.replace(source, "%21", "");
			source = StringUtils.replace(source, "%22", "");
			source = StringUtils.replace(source, "%23", "");
			source = StringUtils.replace(source, "%24", "");
			source = StringUtils.replace(source, "%25", "");
			source = StringUtils.replace(source, "%26", "");
			source = StringUtils.replace(source, "%27", "");
			source = StringUtils.replace(source, "%28", "");
			source = StringUtils.replace(source, "%29", "");
			source = StringUtils.replace(source, "%2A", "");
			source = StringUtils.replace(source, "%2B", "");
			source = StringUtils.replace(source, "%2C", "");
			source = StringUtils.replace(source, "%2D", "");
			source = StringUtils.replace(source, "%2F", "");
			source = StringUtils.replace(source, "%2E", "");
			
			source = StringUtils.replace(source, "%30", "");
			source = StringUtils.replace(source, "%31", "");
			source = StringUtils.replace(source, "%32", "");
			source = StringUtils.replace(source, "%33", "");
			source = StringUtils.replace(source, "%34", "");
			source = StringUtils.replace(source, "%35", "");
			source = StringUtils.replace(source, "%36", "");
			source = StringUtils.replace(source, "%37", "");
			source = StringUtils.replace(source, "%38", "");
			source = StringUtils.replace(source, "%39", "");
			source = StringUtils.replace(source, "%3A", "");
			source = StringUtils.replace(source, "%3B", "");
			source = StringUtils.replace(source, "%3C", "");
			source = StringUtils.replace(source, "%3D", "");
			source = StringUtils.replace(source, "%3F", "");
			source = StringUtils.replace(source, "%3E", "");
			
			source = StringUtils.replace(source, "%40", "");
			
			result = source;
		}
		
		return result;
	}
}

