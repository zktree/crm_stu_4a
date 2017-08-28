<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>树型菜单窗口</title>
<style>


.simpleTree {
font: normal 12px arial, tahoma, helvetica, sans-serif;
    margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
    margin: 0;
    padding: 0;
    /*
    overflow:auto;
    width: 250px;
    height:350px;
    overflow:auto;
    border: 1px solid #444444;
    */
}

.simpleTree li {
    list-style: none;
    margin: 0;
    padding: 0 0 0 34px;
    line-height: 14px;
}

.simpleTree li span {
    display: inline;
    clear: left;
    white-space: nowrap;

}

.simpleTree ul {
    margin: 0;
    padding: 0;
}

.simpleTree .root {
    margin-left: -16px;
    background: url(images/root.gif) no-repeat 16px 0;
}

.simpleTree .line {
    margin: 0 0 0 -16px;
    padding: 0;
    line-height: 3px;
    height: 3px;
    font-size: 3px;
    background: url(images/line_bg.gif) 0 0 no-repeat transparent;
}

.simpleTree .line-last {
    margin: 0 0 0 -16px;
    padding: 0;
    line-height: 3px;
    height: 3px;
    font-size: 3px;
    background: url(images/spacer.gif) 0 0 no-repeat transparent;
}

.simpleTree .line-over {
    margin: 0 0 0 -16px;
    padding: 0;
    line-height: 3px;
    height: 3px;
    font-size: 3px;
    background: url(images/line_bg_over.gif) 0 0 no-repeat transparent;
}

.simpleTree .line-over-last {
    margin: 0 0 0 -16px;
    padding: 0;
    line-height: 3px;
    height: 3px;
    font-size: 3px;
    background: url(images/line_bg_over_last.gif) 0 0 no-repeat transparent;
}

.simpleTree .folder-open {
    margin-left: -16px;
    background: url(images/collapsable.gif) 0 -2px no-repeat;
}

.simpleTree .folder-open-last {
    margin-left: -16px;
    background: url(images/collapsable-last.gif) 0 -2px no-repeat;
}

.simpleTree .folder-close {
    margin-left: -16px;
    background: url(images/expandable.gif) 0 -2px no-repeat;
}

.simpleTree .folder-close-last {
    margin-left: -16px;
    background: url(images/expandable-last.gif) 0 -2px no-repeat;
}

.simpleTree .doc {
    margin-left: -16px;
    background: url(images/leaf.gif) 0 -1px no-repeat;
}

.simpleTree .doc-last {
    margin-left: -16px;
    background: url(images/leaf-last.gif) 0 -1px no-repeat;
}

.simpleTree .ajax {
    background: url(images/spinner.gif) no-repeat 0 0;
    height: 16px;
    display: none;
}

.simpleTree .ajax li {
    display: none;
    margin: 0;
    padding: 0;
    
}

.simpleTree .trigger {
    display: inline;
    margin-left: -32px;
    width: 28px;
    height: 11px;
    cursor: pointer;
}

.simpleTree .text {
    cursor: hand;
}

.simpleTree .active {
    cursor: default;
    background-color: #F7BE77;
    padding: 0px 2px;
    border: 1px dashed #444;
}

#drag_container {
    background: #ffffff;
    color: #000;
    font: normal 11px arial, tahoma, helvetica, sans-serif;
    border: 1px dashed #767676;
}

#drag_container ul {
    list-style: none;
    padding: 0;
    margin: 0;
}

#drag_container li {
    list-style: none;
    background-color: #ffffff;
    line-height: 18px;
    white-space: nowrap;
    padding: 1px 1px 0px 16px;
    margin: 0;
}

#drag_container li span {
    padding: 0;
}

#drag_container li.doc,#drag_container li.doc-last {
    background: url(images/leaf.gif) no-repeat -17px 0 #ffffff;
}

#drag_container .folder-close,#drag_container .folder-close-last {
    background: url(images/expandable.gif) no-repeat -17px 0 #ffffff;
}

#drag_container .folder-open,#drag_container .folder-open-last {
    background: url(images/collapsable.gif) no-repeat -17px 0 #ffffff;
}

.contextMenu {
    //display: none;
}
</style>
<script type="text/javascript" src="css_js/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="css_js/jquery.simple.tree.js"></script>
<script type="text/javascript">
var simpleTreeCollection;
$(document).ready(function(){
	simpleTreeCollection = $('.simpleTree').simpleTree({
		autoclose: false,
		afterClick:function(node){
			window.opener.location.href = "productType/toUpdate?productTypeId="+node.attr("id");
			window.close();
		},
		afterDblClick:function(node){
			//alert("text-"+$('span:first',node).text());
		},
		//afterMove:function(destination, source, pos){
			//alert("destination-"+destination.attr('id')+" source-"+source.attr('id')+" pos-"+pos);
		//},
		afterAjax:function()
		{
			//alert('Loaded');
		},
		animate:true
		//,docToFolderConvert:true
	});
});
</script>
</head>
<body>
	${str}
</body>
</html>