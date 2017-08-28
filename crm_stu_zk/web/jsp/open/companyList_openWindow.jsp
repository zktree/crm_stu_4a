<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>客户拜访</title>
	<script type="text/javascript" src="css_js/jquery/jquery-1.3.2.min.js"></script>
	<script src="css_js/jquery.tablesorter.min.js" type="text/javascript"></script>
	<link href="css_js/index.css" rel="stylesheet" type="text/css">
	<script src="css_js/popup4nextTDate.js" type="text/javascript"></script>
	<script src="css_js/popup4exExcel.js" type="text/javascript"></script>

	<link href="css_js/general.css" rel="stylesheet" type="text/css"></link>
	<link href="css_js/calendar.css" rel="stylesheet" type="text/css"></link>
	<script src="css_js/cal.js" type="text/javascript"></script>

	<script type="text/javascript">
        $(function(){
            //获得每页显示几条数据
            var pageSize="${pager.pageSize}";
            //获得所有选项
            var options=$("#_pageSize_down option");
            //遍历所有选项
            for(var i=0;i<options.length;i++){
                //如果pageSize==某个选项的value值
                if(pageSize==$(options[i]).val()){
                    $(options[i]).attr("selected","selected");//添加选中
                    break;//for结束
                }
            }

        });



        //表格排序
        $(document).ready(function(){
            $("#select_row").tablesorter({headers:{0:{sorter:false}}})
        });




        function getNextTDate(){
            var tdate = $(window.parent.frames["nextTDatePage"].document).find(":input#nextTouchDate");
            $("#nextTouchDate").val(tdate.val());
            if($("#nextTouchDate").val()!="点击显示时间列表"){
                document.forms[0].submit();
            }
        }
        function toChange(){
            var cball = $("#ids");
            var cbs = $("[name=company_id]");
            if(cball.attr("checked") != ""){
                cball.attr("checked","checked");
            }else{
                cball.attr("checked","");
            }
            for(var i = 0; i < cbs.length; i++){
                cbs[i].checked = cball.attr("checked");
            }
        }
        function toUrl(pageNumId,pageSizeId){
            var _pageNum,_pageSize;
            if(pageNumId != "_null"){
                _pageNum = document.getElementById(pageNumId);
            }else{
                _pageNum = document.getElementById("_pageNum_up");
            }
            var hiddenPageNum = document.getElementById("hiddenPageNum");
            if("" != hiddenPageNum.value){
                hiddenPageNum.value = _pageNum.options[_pageNum.selectedIndex].value;
            }
            if(pageSizeId != "_null"){
                _pageSize = document.getElementById(pageSizeId);
            }else{
                _pageSize = document.getElementById("_pageSize_up");
            }
            var hiddenPageSize = document.getElementById("hiddenPageSize");
            if("" != hiddenPageSize.value){
                hiddenPageSize.value = _pageSize.options[_pageSize.selectedIndex].value;
            }
            document.forms[0].submit();
        }
        function toUrl2(select_id){
            var num=$("#"+select_id+" option:selected").val();
            $("#hiddenPageSize").val(num);
            $("#hiddenPageNum").val(1);//把页码改为1
            document.forms[0].submit();

        }
        function initPageSize(){
            var pageSizes = document.getElementsByName("_pageSize");
            for(var o = 0; o < pageSizes.length; o++){
                for(var i = 0; i < pageSizes[o].options.length; i++){
                    if(pageSizes[o].options[i].value == '${pageSize}'){
                        pageSizes[o].options[i].selected="selected";
                    }
                }
            }

        }
        function nextPage(){
            var hiddenPageNum = document.getElementById("hiddenPageNum");
            if("" != hiddenPageNum.value){
                hiddenPageNum.value = hiddenPageNum.value - 0 + 1;
                document.forms[0].submit();
            }
        }
        function prePage(){
            var hiddenPageNum = document.getElementById("hiddenPageNum");
            if("" != hiddenPageNum.value){
                hiddenPageNum.value = hiddenPageNum.value - 0;
                if(hiddenPageNum.value > 1){
                    hiddenPageNum.value -= 1;
                }
                document.forms[0].submit();
            }
        }
        function firstPage(){
            var hiddenPageNum = document.getElementById("hiddenPageNum");
            if("" != hiddenPageNum.value){
                hiddenPageNum.value = 1;
                document.forms[0].submit();
            }
        }
        function lastPage(){
            var hiddenPageNum = document.getElementById("hiddenPageNum");
            if("" != hiddenPageNum.value){
                hiddenPageNum.value = ${pager.totalPage};
                document.forms[0].submit();
            }
        }
        function submitForm(){
            document.forms[0].submit();
        }
        function resetForm(){
            $(".addCText").val("");
        }

        function conrAdvanceSelect(){
            var ff = !(document.all);
            var con = document.getElementById("_conradvanceSelect");
            var tab = document.getElementById("advanceSelect");
            if(tab.style.display=="none"){
                if(ff){
                    tab.style.display="table-row";
                }else{
                    tab.style.display="block";
                }

                con.innerHTML="(高级搜索↑)";
            }else{
                tab.style.display="none"
                con.innerHTML="(高级搜索↓)";
            }
        }
        function getCityList(){
            //取得省份
            var province_str = $("#province_id").val();
            //将省份进行escape
            var queryString = {name:province_str};
            //通过ajax方式传递数据
            $.ajax({
                type:"post",
                url:"getCities.action",
                data:queryString,
                dataType:"json",
                success: function(msg) {
                    $("#city_id").html("");
                    var str = "<option value=1>---</option>";
                    for(var i = 0; i < msg.SCities.length;i++){
                        //alert(msg.SCities[i].name);
                        str+="<option value="+msg.SCities[i].name+">"+msg.SCities[i].name+"</option>"
                    }
                    $("#city_id").html(str);
                }
            });
        }
        function toDel(){
            var cbNum = checkBoxNum();
            if(cbNum > 0){
                if(window.confirm("确定删除？")){

                    document.getElementById("isDel").value="del";
                    document.forms[0].submit();

                }
            }
        }
        //判断列表复选框选中个数是否大于0，是：返回个数，否：返回0
        function checkBoxNum(){
            if($("input[name='order_id']:checked").length > 0){
                return $("input[name='order_id']:checked").length;
            }else{
                return 0;
            }
        }
        function showPopUp(){
            var cbNum = checkBoxNum();
            if(cbNum > 0){
                loadPopup4nextTDate("批量设置下次联系时间");
            }
        }

        function exportExcel(){
            alert(1234567)
            loadPopup_excel('导出Excel');
            alert(12345)
        }
        function setOpenerValue(id_str,name_str){

            try{
                window.opener.document.getElementById("cId").value = id_str;
//                window.opener.document.getElementById("companyName").value = name_str;
                window.opener.document.getElementById("cName").value=name_str;

            }catch(e){

            }
            window.close();
        }
	</script>
</head>
<body onload="initPageSize()">
<form name="form1" id="load4Visit" method="post" action="open/companyList">

	<table width="99%" border="0" cellspacing="0" cellpadding="0"
		   id="index_content">
		<tr>
			<td valign="middle">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" id="index_main_div1">
					<tr>
						<td height="21" background="image/index_main_div_titleBg.gif">
							<img style="margin-left: 5px;" src="img/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;
							<span style="font-weight: bold; font-size: 12px;">客户资料</span>
						</td>
					</tr>
				</table>

				<table width="100%" border="0" cellspacing="0" cellpadding="0" id="index_main_div1">
					<tr>
						<td height="21" background="image/index_main_div_titleBg.gif">
							<img style="margin-left: 5px;" src="img/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;
							<span style="font-weight: bold; font-size: 12px;">客户搜索</span>
						</td>
					</tr>
					<tr>
						<td height="56" align="left" valign="top" bgcolor="#f7fbfc">

							<table width="100%">
								<tr>
									<td width="100%">
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											   height="100%" id="selectTable">
											<tr>
												<td width="11%">客户性质:</td>
												<td width="17%">
													<input type="text" name="quality" class="addCText" value="${company.quality}"></td>
												<td width="11%">客户名称:</td>
												<td width="17%">
													<input type="text" name="name" class="addCText" value="${company.name}"></td>
												<td width="11%">所属人:</td>
												<td width="17%">
													<select name="ownerUsr" class="addCText">
														<option value="">----</option>
														<c:forEach items="${users}" var="user">
															<option value="${user.id}">${user.name}</option>
														</c:forEach>
													</select>
												</td>
												<td width="8%"><img src="image/s1.gif" width="59" height="22" onclick="submitForm()" alt="搜索按钮"></td>
												<td width="7%" align="left"><img src="image/s2.gif" width="62" height="22" onclick="resetForm()"></td>
												<td width="1%" align="left">&nbsp;</td>
											</tr>
											<tr>
												<td>电话1:</td>
												<td><input type="text" name="tel1" class="addCText" value="${company.tel1}"></td>
												<td>拼音码:</td>
												<td><input type="text" name="pycode" class="addCText" value="${company.pycode}"></td>
												<td>传真:</td>
												<td><input type="text" name="fax" class="addCText" value="${company.fax}"></td>
												<td colspan="3">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr style="display: none" id="advanceSelect">
									<td width="100%">&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>




				<table width="100%" border="0" cellspacing="0" cellpadding="0" id="select_table">
					<tr><td height="10" bgcolor="#f7fbfc">&nbsp;</td></tr>
					<tr>
						<td height="11"><img src="image/t1.gif" align="absmiddle">
							<span style="font-size: 14; font-weight: bold;">客户列表</span></td>
					</tr>
					<tr>
						<td height="16" valign="top"><br /></td>
					</tr>
					<tr>
						<td height="5" valign="top"></td>
					</tr>
					<tr>
						<td height="28" valign="top">
							<input type="hidden" name="page" value="${pager.page}" id="hiddenPageNum" />
							<input type="hidden" name="pageSize" value="${pager.pageSize}" id="hiddenPageSize" />
							<input type="hidden" name="isDel" value="del" id="isDel" />
							<input type="hidden" name="exportType" value="" id="exportType" />
							<input type="hidden" name="nextTouchDate_tmp" value="" id="nextTouchDate" />
							<input type="hidden" name="MType" value="companyList_openWindow" id="MType" />
							<table width="100%" border="0" cellspacing="0" cellpadding="0" id="selectTable_content">
								<tr>
									<td width="40%" height="19" bgcolor="#f2faff" style="font-size: 12px;">&nbsp;
										<img src="image/t2.gif" align="absmiddle" width="15" height="16">

										<span onclick="exportExcel()" class="pager" style="cursor: pointer">导出</span>
										| 选择条目:<span id="selected_num1">0</span>

									</td>
									<td width="3%" bgcolor="#f2faff" style="font-size: 12px;">&nbsp;</td>
									<td width="57%" bgcolor="#f2faff" align="right" style="font-size: 12px;">共${pager.totalRecord}条
										<span class="pager" onclick="firstPage();" style="cursor: pointer">首页</span>
										<span class="pager" onclick="prePage();" style="cursor: pointer">上一页</span>
										${pager.page}/${pager.totalPage}
										<span onclick="nextPage()" class="pager" style="cursor: pointer">下一页</span>
										<span onclick="lastPage()" class="pager" style="cursor: pointer">末页</span>
										<select name="_pageNum" onchange="toUrl('_pageNum_up','_null');" id="_pageNum_up">
											<c:forEach begin="1" end="${pager.totalPage}" varStatus="sta">
												<option value="${sta.index }" <c:if test="${pager.page==sta.index }">selected="selected"
												</c:if>>第${sta.index }页</option>
											</c:forEach>
										</select>
										<select name="_pageSize" onchange="toUrl2('_pageSize_up')" id="_pageSize_up">
											<option value="5" <c:if test="${pager.pageSize==5 }">selected="selected"</c:if>>5条</option>
											<option value="6" <c:if test="${pager.pageSize==6 }">selected="selected"</c:if>>6条</option>
											<option value="7" <c:if test="${pager.pageSize==7 }">selected="selected"</c:if>>7条</option>
											<option value="8" <c:if test="${pager.pageSize==8 }">selected="selected"</c:if>>8条</option>
											<option value="9" <c:if test="${pager.pageSize==9 }">selected="selected"</c:if>>9条</option>
											<option value="10" <c:if test="${pager.pageSize==10 }">selected="selected"</c:if>>10条</option>
											<option value="15" <c:if test="${pager.pageSize==15 }">selected="selected"</c:if>>15条</option>
											<option value="20" <c:if test="${pager.pageSize==20 }">selected="selected"</c:if>>20条</option>
											<option value="25" <c:if test="${pager.pageSize==25 }">selected="selected"</c:if>>25条</option>
											<option value="30" <c:if test="${pager.pageSize==30 }">selected="selected"</c:if>>30条</option>
											<option value="50" <c:if test="${pager.pageSize==50 }">selected="selected"</c:if>>50条</option>
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="3" bgcolor="#f2faff">

										<table width="100%" border="0" cellspacing="0" cellpadding="0" id="select_row">
											<thead>
											<tr>
												<th width="10%" height="28" align="center"
													background="image/select_title_title.jpg">操作</th>
												<th width="12%" align="left"
													background="image/select_title_title.jpg">客户编码</th>
												<th width="14%" align="left"
													background="image/select_title_title.jpg">客户名称</th>
												<th width="13%" align="center"
													background="image/select_title_title.jpg"><strong>客户性质</strong></th>
												<th width="12%" align="center"
													background="image/select_title_title.jpg"><strong>客户等级</strong></th>
												<th width="12%" align="center"
													background="image/select_title_title.jpg"><strong>电话一</strong></th>
												<th width="27%" align="center"
													background="image/select_title_title.jpg"><strong>电子邮件</strong></th>
											</tr>
											</thead>
											<tbody>
											<c:forEach items="${companyList}" var="company">
												<tr class="select_content_bg">
													<td align="center">
														<img src="image/xuanze.gif" align="absmiddle" style="cursor:pointer;" onclick="setOpenerValue('${company.id}','${company.name}')"> 									</td>
													<td>${company.code}</td>
													<td><a href="loadLinkMan.action?id=${company.id}" class="normal">${company.name}</a></td>
													<td align="center">${company.quality}</td>
													<td align="center">${company.grade}</td>
													<td align="center">${company.tel1}</td>
													<td align="center">${company.email}</td>
												</tr>
											</c:forEach>
											</tbody>
										</table>
									</td>
								</tr>
								<tr>
									<td width="40%" height="19" bgcolor="#f2faff"
										style="font-size: 12px;">&nbsp;<img src="image/t2.gif"
																			align="absmiddle" width="15" height="16"> 导出 | 选择条目:0</td>
									<td width="3%" bgcolor="#f2faff" style="font-size: 12px;">&nbsp;</td>
									<td width="57%" bgcolor="#f2faff" align="right" style="font-size: 12px;">共${pager.totalRecord}条
										<span class="pager" onclick="firstPage();" style="cursor: pointer">首页</span>
										<span class="pager" onclick="prePage();" style="cursor: pointer">上一页</span>
										${pager.page}/${pager.totalPage} <span onclick="nextPage()" class="pager" style="cursor: pointer">下一页</span>
										<span onclick="lastPage()" class="pager" style="cursor: pointer">末页</span>
										<select name="_pageNum" onchange="toUrl('_pageNum_down','_null');" id="_pageNum_down">
											<c:forEach begin="1" end="${pager.totalPage}" varStatus="sta">
												<option value="${sta.index }" <c:if test="${pager.page==sta.index }">selected="selected"</c:if>>第${sta.index }页</option>
											</c:forEach>
										</select>
													<select name="_pageSize" onchange="toUrl2('_pageSize_down')" id="_pageSize_down">
														<option value="5">5条</option>
														<option value="6">6条</option>
														<option value="7">7条</option>
														<option value="8">8条</option>
														<option value="9">9条</option>
														<option value="10">10条</option>
														<option value="15">15条</option>
														<option value="20">20条</option>
														<option value="25">25条</option>
														<option value="30">30条</option>
														<option value="50">50条</option>
													</select>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<input type="hidden" id="nextTouchDate" value="" name="nextTouchDate"/>
			</td>
		</tr>
	</table>
</form>
</body>

</html>