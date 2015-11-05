/**
 * 首页模块点击事件
 * @param url 要跳转的路径
 * @param name 导航名称
 */

$(document).on("click",".menu",function() {loadContent(this);});
$(function(){
	var url = location.href;
	var newUrl = url.substring(url.indexOf("#"));
	if(url.indexOf("#") < 0) newUrl='main';
	$("#indexLeftMenuContent a").each(function(){
		if($(this).attr("href").indexOf(newUrl)!=-1){loadContent(this);}
	});
	try {
		ace.settings.check('main-container', 'fixed');
		ace.settings.check('navbar', 'fixed');
		ace.settings.check('breadcrumbs', 'fixed');
		ace.settings.check('sidebar', 'fixed');
		ace.settings.check('sidebar', 'collapsed');
	} catch (e) {
	}
});
function loadContent(str){
	var url = $(str).attr("href").substring(1);
	$("#page_content_title").text($(str).text());
	$("#indexLeftMenuContent li").removeClass("active").removeClass("open");
	$(str).parent().addClass("active");
	$(str).parents(".submenu").parent().addClass("active").addClass("open");
	var index = layer.load(2,{scrollbar: false,shade: [0.2, '#393D49']});
	$("#page_content").load(url+".html?r="+Math.random(),function(){
		layer.close(index);
	});
}
function clickLoad(url,contentId){
	var index = layer.load();
	$("#"+contentId).load($("#path").val()+"/"+url+"&r="+Math.random(),function(){
		layer.close(index);
	});
}
