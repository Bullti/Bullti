/**
 * 
 */

 
$(document).ready(function() {
	var page=/*[[${param.page}]]*/null;
	var pageLimit=/*[[${pu.pageLimit}]]*/null;
	if(page==null) page="1";
	var idx= parseInt(page)%pageLimit-1;
	
	const pageBtns = $('.btn-page').eq(idx).addClass('target');
});