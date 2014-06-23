package com.skt.apollo.models.common;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PagingBean {
	
	public String order;
	public String searchYn;
	public String searchType;					//검색구분
	public String searchValue;					//검색어
	public String startDate;					//검색시작일
	public String endDate;						//검색종료일
	
	public long totalCount = 0L;
	
	public int pagesize = 10;
	public int pageno = 1;
	public int pagecount = 0;
	
	public int excludeRowsCount = 0;		// for mysql
	
	
	public String actionType ;
	
	
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSearchYn() {
		return searchYn;
	}
	public void setSearchYn(String searchYn) {
		this.searchYn = searchYn;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public int getPagecount() {
		return pagecount;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	public int getExcludeRowsCount() {
		return excludeRowsCount;
	}
	public void setExcludeRowsCount(int excludeRowsCount) {
		this.excludeRowsCount = excludeRowsCount;
	}
	
	public void setPaging() {
		pagecount = 0;
		if (( totalCount % pagesize) == 0 ){
			pagecount = (int) (totalCount / pagesize);
		}else{
			pagecount = (int) ((totalCount / pagesize) + 1);
		}
		if ( pageno >= pagecount ){
			pageno = pagecount;
		}
		
		excludeRowsCount = (pageno-1) * pagesize;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
