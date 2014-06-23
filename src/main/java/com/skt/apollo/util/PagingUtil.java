package com.skt.apollo.util;

import com.skt.apollo.models.common.PagingBean;


public class PagingUtil {
	
	public static String CreatePaging(PagingBean pagingBean) {
		
		String wholePaging = "";
		
		if ( pagingBean != null ) {
			int Pgno = pagingBean.pageno;
			int Pgcount = pagingBean.pagecount;
			int Pgnum = pagingBean.pagesize;
		
			int currentpageno = ((((Pgno - 1)/Pgnum)) * Pgnum) + 1;
			
			int pre10_page = 0;
			if ((currentpageno - Pgnum) < 1 ) {
				pre10_page = 1;
			} else {
				pre10_page = currentpageno - Pgnum;
			}
		
			int next10_page = 0;
			if ( Pgcount < (currentpageno + Pgnum) ) {
				next10_page = Pgcount;
			} else {
				next10_page = currentpageno + Pgnum;
			}
			
			String frontPage = "<div class='paging'>";
			
			// 처음
			String initPage = "";
			if ( Pgno > Pgnum ) {			
				initPage = "<a href='javascript:goPage(1);'><img src='/resources/images/btn_first.png' alt='' /></a>";
			} else {
				initPage = "<a href='javascript:void(0);'><img src='/resources/images/btn_first.png' alt='' /></a>";
			}
		
			// 이전 10페이지
			String pre10Page = "";
			if ( Pgno > 1 ) {
				int tmpNo = Pgno - 1;
				pre10Page = "<a href='javascript:goPage("+ pre10_page +");'><img src='/resources/images/btn_prev.png' alt='' /></a><em>";
			} else {
				pre10Page = "<a href='javascript:void(0);'><img src='/resources/images/btn_prev.png' alt='' /></a><em>";
			}
		
			// 페이징
			String paging = "";
			for ( int i = currentpageno; i < (currentpageno+Pgnum); i++ ) {
				if ( i <= Pgcount ) {
					if (i == Pgno) {
						paging = paging + "<a href='javascript:void(0);' class='on'>" + i + "</a>";
					} else {
						paging = paging + "<a href='javascript:goPage("+ i +");'>" + i + "</a>";
					}
				}
			}
		
			// 다음페이지
			String next10Page = "";
			if ( Pgno < Pgcount ) {
				int tmpNo = Pgno + 1;
				next10Page = "</em><a href='javascript:goPage("+ next10_page +");'><img src='/resources/images/btn_next.png' alt='' /></a>";
			} else {
				next10Page = "</em><a href='javascript:void(0);'><img src='/resources/images/btn_next.png' alt='' /></a>";
			}
		
			// 끝
			String endPage = "";
			if ( Pgcount > Pgnum ) {
				endPage = "<a href='javascript:goPage("+Pgcount+");'><img src='/resources/images/btn_end.png' alt='' /></a>";
			} else {
				endPage = "<a href='javascript:void(0);'><img src='/resources/images/btn_end.png' alt='' /></a>";
			}

			String backPage = "</div>";
		
			wholePaging = frontPage + initPage + "&nbsp;" + pre10Page + paging + next10Page + "&nbsp;" + endPage + backPage;
		}
		
		return wholePaging;
	}
	
	public static String CreateAdminPaging(PagingBean pagingBean) {
		
		String wholePaging = "";
		
		if ( pagingBean != null ) {
			int Pgno = pagingBean.pageno;
			int Pgcount = pagingBean.pagecount;
			int Pgnum = pagingBean.pagesize;
		
			int currentpageno = ((((Pgno - 1)/Pgnum)) * Pgnum) + 1;
		
			int pre10_page = 0;
			if ((currentpageno - Pgnum) < 1 ) {
				pre10_page = 1;
			} else {
				pre10_page = currentpageno - Pgnum;
			}
		
			int next10_page = 0;
			if ( Pgcount < (currentpageno + Pgnum) ) {
				next10_page = Pgcount;
			} else {
				next10_page = currentpageno + Pgnum;
			}
		
			String frontPage = "<div class='pagination pagination-centered'>";
			frontPage = frontPage + "<ul>";
			
			// 처음
			String initPage = "";
			if ( Pgno > Pgnum ) {			
				initPage = "<li><a href='javascript:goPage("+pre10_page+");'>&laquo;</a></li>";
			} else {
				initPage = "<li class='disabled'><a href='javascript:void(0);'>&laquo;</a></li>";
			}
		
			// 이전 10페이지
			String pre10Page = "";
			if ( Pgno > 1 ) {
				int tmpNo = Pgno - 1;
				pre10Page = "<li><a href='javascript:goPage("+ tmpNo +");'>&lt;</a></li>";
			} else {
				pre10Page = "<li class='disabled'><a href='javascript:void(0);'>&lt;</a></li>";
			}
		
			// 페이징
			String paging = "";
			for ( int i = currentpageno; i < (currentpageno+Pgnum); i++ ) {
				if ( i <= Pgcount ) {
					if (i == Pgno) {
						paging = paging + "<li class='active'><a href='javascript:void(0);'>" + i + "</a></li>";
					} else {
						paging = paging + "<li><a href='javascript:goPage("+ i +");'>" + i + "</a></li>";
					}
				}
			}
		
			// 다음페이지
			String next10Page = "";
			if ( Pgno < Pgcount ) {
				int tmpNo = Pgno + 1;
				next10Page = "<li><a href='javascript:goPage("+ tmpNo +");'>&gt;</a></li>";
			} else {
				next10Page = "<li class='disabled'><a href='javascript:void(0);'>&gt;</a></li>";
			}
		
			// 끝
			String endPage = "";
			if ( Pgcount > Pgnum ) {
				endPage = "<li><a href='javascript:goPage("+next10_page+");'>&raquo</a></li>";
			} else {
				endPage = "<li class='disabled'><a href='javascript:void(0);'>&raquo</a></li>";
			}
			
			String backPage = "</ul>";
			backPage = backPage + "</div>";
		
			wholePaging = frontPage + initPage + pre10Page + paging + next10Page + endPage + backPage;
		}
		
		return wholePaging;
	}
	
}
