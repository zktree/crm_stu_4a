// JavaScript Document

var popupStatus = 0;
function loadPopup(objname){
	//判断浏览器类型
	if(window.ActiveXObject){
		//第一种解决方案：通过一个透明的iframe解决
		$("#backgroundPopup").html("<iframe style='width:100%;height:100%;filter:alpha(opacity=0);-moz-opacity:0'></iframe>");
		//第二种方案：直接隐藏所有的select
		//取得页面中所有的select对像，也就是一个数组
		//var selects = document.getElementsByTagName("select");
		//for(var i = 0; i < selects.length;i++){
		//	selects[i].style.display = "none";
		//}
	}
	var queryString = {code:objname};
	//通过ajax方式传递数据
	$.ajax({
		type:"post",
		url:"getDictinaryDetails.action",
		data:queryString,
		dataType:"json",
		success: function(msg) {
			$("#dictionaryMsg").html("");
			$("#contactArea").html("");
			var str = "<input type='button' value='保存' onclick=updateValue('"+objname+"');>&nbsp;&nbsp;<br/><br/>";
			if(msg.SDictionaryDetails.length < 1){
				str += "<br/><input type='text' value='' class='newvalue'/>";
				$("#contactArea").html(str);
				$("#popupContact h1").html("编辑选项");
			}else{
				for(var i = 0; i < msg.SDictionaryDetails.length; i++){
					str += "<br/><input type='text' class='oldvalue' name='"+msg.SDictionaryDetails[i].id+"' value='"+msg.SDictionaryDetails[i].value+"'/><input type='button' value='向上' onclick=upsort('"+msg.SDictionaryDetails[i].sort+"','"+objname+"')><input type='button' value='向下' onclick=downsort('"+msg.SDictionaryDetails[i].sort+"','"+objname+"')><input type='button' value='删除' onclick=del('"+msg.SDictionaryDetails[i].id+"','"+objname+"')>";
				}
			
				str += "<br/><input type='text' value='' class='newvalue'/>";
				$("#contactArea").html(str);
				$("#popupContact h1").html("编辑--"+msg.SDictionaryDetails[0].SDictionaryClass.name);
			}
			centerPopup();
			if(popupStatus == 0){
				$("#backgroundPopup").css({
					"opacity":"0.3"						  
				});
				$("#popupContact").fadeIn("slow");
				$("#backgroundPopup").fadeIn("slow");
				popupStatus = 1;
			}
			//change selectoption value
			
        }
	});
	
}

function updateValue(objname) {
	var objs = $("#contactArea .oldvalue");
	var addObj = $("#contactArea .newvalue").val();

	var objstr = "";
	for ( var i = 0; i < objs.length; i++) {
		objstr += objs[i].name + "@" + objs[i].value + ",";
	}
	var queryString = {
		value : objstr,
		newValue : addObj,
		objname : objname
	};
	$.ajax( {
		type : "post",
		url : "updateDictionaryDetails.action",
		data : queryString,
		dataType : "json",
		success : function(msg) {
			loadPopup(objname);
			getNewVal(objname);
			$("#dictionaryMsg").html("修改成功！");
		}
	});

}
function upsort(sort,objname){
	var queryString = {sort : sort,code : objname};
	$.ajax({
		type:"post",
		url:"upsortDictionaryDetails.action",
		data:queryString,
		dataType:"json",
		success: function(msg) {
			loadPopup(objname);
			getNewVal(objname);
			$("#dictionaryMsg").html("向上移动成功！");
		}
	});
}
function downsort(sort,objname){
	var queryString = {sort : sort,code : objname};
	$.ajax({
		type:"post",
		url:"downsortDictionaryDetails.action",
		data:queryString,
		dataType:"json",
		success: function(msg) {
			loadPopup(objname);
			getNewVal(objname);
			$("#dictionaryMsg").html("向下移动成功！");
		}
	});
}
function del(_id,objname){
	var queryString = {id : _id};
	$.ajax({
		type:"post",
		url:"deleteDictionaryDetails.action",
		data:queryString,
		dataType:"json",
		success: function(msg) {
			loadPopup(objname);
			getNewVal(objname);
			$("#dictionaryMsg").html("删除成功！");
		}
	});
}
function centerPopup(){
	var windowWidth = document.body.clientWidth;
	var windowHeight = document.body.scrollTop+(document.documentElement.clientHeight/2);
	
	var popupWidth = $("#popupContact").width();
	var popupHeight = $("#popupContact").height();
	
	$("#popupContact").css({
		"position":"absolute",
		"top": windowHeight,
		"left":windowWidth/2 - popupWidth/2,
		"width":384,
		"height":408
	});
	
	$("#contactArea").css({
		"width":390,
		"height":300
	});
	
}

function disablePopup(){
	if(popupStatus == 1){
		
		$("#popupContact").fadeOut("slow");
		$("#backgroundPopup").fadeOut("slow");
		popupStatus = 0;
		
		//var selects = document.getElementsByTagName("select");
		//for(var i = 0; i < selects.length;i++){
		//	selects[i].style.display = "block";
		//}
	}
}

$(document).ready(function(){
		$("#popupContactClose").click(function(){
			disablePopup();	
		});
		
		//$("#backgroundPopup").click(function(){
		//	disablePopup();	
		//});
	
});