<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<base href="<%=basePath%>"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css_js/index.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="css_js/jquery/jquery-1.3.2.min.js"></script>
	<script src="css_js/popup4exExcel.js" type="text/javascript"></script>
	<link href="css_js/general.css" rel="stylesheet" type="text/css"></link>
	<title>添加商品类别</title>
	<script type="text/javascript">

        function dataempty() {
			$("input[class='inputTextStyle']").val("");
        }


		//改变选中数目
		function changeNum() {
            //获得选中的数目
            var num=$("input[name='product_id']:checked").length;
            //获得所有多选框的数目
            var totalnum=$("input[name='product_id']").length;
            //给选择条目赋值
            $("#selected_num1").text(num);
            $("#selected_num2").text(num);

            if(num==totalnum){
                $("#ids").attr("checked",true);
            }else{
                $("#ids").attr("checked",false);
            }

        }


		//全选
		function toChange() {
            //获得是否选中
            var flag=$("#ids").attr("checked");
            //根据上边多选框状态改变下边多选框状态
            $("input[name='product_id']").attr("checked",flag);

            //获得选中的数目
            var num=$("input[name='product_id']:checked").length;
            //给选择条目赋值
            $("#selected_num1").text(num);
            $("#selected_num2").text(num);
			
        }



		//每页几条数据
        function toUrl2(select_id){
            var options=$("#"+select_id+" option");
            for(var i=0;i<options.length;i++){
                //alert($(options[i]).attr("selected"));
                if($(options[i]).attr("selected")==true){
                    var num=$(options[i]).val();
                    $("#hiddenPageSize").val(num);
                    break;
                }
            }
            $("#hiddenPageNum").val(1);//把页码改为1
            document.forms[0].submit();

        }

		//
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
            if($("input[name='product_id']:checked").length > 0){
                return $("input[name='product_id']:checked").length;
            }else{
                return 0;
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

        function nextPage(){
            var hiddenPageNum = document.getElementById("hiddenPageNum");
            if("" != hiddenPageNum.value){
                var totalNum="${pager.totalPage}";//总页数
                if((hiddenPageNum.value-0)<(totalNum-0)){
                    hiddenPageNum.value = hiddenPageNum.value - 0 + 1;
                    document.forms[0].submit();
                }
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
            //去空格
			var property = $("input[name = name]").val().trim();
            $("input[name = name]").val(property);
            var property = $("input[name = code]").val().trim();
            $("input[name = code]").val(property);
            var property = $("input[name = pycode]").val().trim();
            $("input[name = pycode]").val(property);
            var property = $("input[name = productTypeName]").val().trim();
            $("input[name = productTypeName]").val(property);
            var property = $("input[name = provider]").val().trim();
            $("input[name = provider]").val(property);
            var property = $("input[name = lowerPrice]").val().trim();
            $("input[name = lowerPrice]").val(property);
            var property = $("input[name = upperPrice]").val().trim();
            $("input[name = upperPrice]").val(property);
        }
        function resetForm(){
            document.forms[0].reset();
        }
        function exportExcel(){
            loadPopup_excel('导出Excel');
        }
        function openTreeWindow(){
            window.open("optionTreeWidnow.action","_blank"
            );
        }
	</script>
</head>
<body>
<form action="product/list" method="post" name="form1" id="form1">
	<input type="hidden" name="page" value="${pager.page}" id="hiddenPageNum" />
	<input type="hidden" name="pageSize" value="${pager.pageSize}" id="hiddenPageSize" />
	<input type="hidden" name="isDel" value="del" id="isDel" />
	<input type="hidden" name="exportType" value="" id="exportType" />
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
		   id="index_content">
		<tr>
			<td valign="middle">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					   id="index_main_div1">
					<tr>
						<td height="21" background="image/index_main_div_titleBg.gif">
							<img style="margin-left: 5px;" src="image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;
							<span style="font-weight: bold; font-size: 12px;">商品类别搜索</span>
						</td>
					</tr>
					<tr>
						<td height="56" align="left" valign="top" bgcolor="#f7fbfc">
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								   height="100%" id="selectTable">
								<tr>
									<td width="10%">商品名称:</td>
									<td width="18%"><input type="text" value="${product.name}" name="name" class="inputTextStyle"></td>
									<td width="9%">商品编码:</td>
									<td width="18%"><input type="text" name="code" value="${product.code}" class="inputTextStyle"></td>
									<td width="14%">拼音码:</td>
									<td width="15%"><input type="text" name="pycode" value="${product.pycode}" class="inputTextStyle" /></td>
									<td width="7%"><img src="image/s1.gif" width="59" height="22" onclick="submitForm()" alt="搜索按钮"></td>
									<td width="7%" align="left"><img src="image/s2.gif" width="62" height="22" onclick="dataempty()"></td>
									<td width="2%" align="left">&nbsp;</td>
								</tr>
								<tr>
									<td width="10%">商品类别:</td>
									<td width="18%"><input type="text" value="${product.productTypeName}" name="productTypeName" class="inputTextStyle"></td>
									<td width="9%">供应商:</td>
									<td width="18%"><input type="text" value="${product.provider}" name="provider" class="inputTextStyle"></td>
									<td width="14%">单价:</td>
									<td width="15%">从 <input type="text" value="${product.lowerPrice}" name="lowerPrice" size="5" /> 到 <input type="text" value="${product.upperPrice}" name="upperPrice" size="5" /></td>
									<td width="7%">&nbsp;</td>
									<td width="7%" align="left">&nbsp;</td>
									<td width="2%" align="left">&nbsp;</td>
								</tr>
							</table>

						</td>
					</tr>
				</table>


				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					   id="select_table">
					<tr>
						<td height="10" bgcolor="#f7fbfc">&nbsp;</td>
					</tr>
					<tr>
						<td height="11"><img src="image/t1.gif" align="absmiddle">
							<span style="font-size: 14px; font-weight: bold;">商品资料列表</span></td>
					</tr>
					<tr>
						<td height="16" valign="top"><br />
							<img src="image/s3.gif" width="62" height="22"  onclick="javascript:window.location.href='product/toAdd'">
							<img src="image/s7.gif" width="59" height="22" alt="删除按钮" onclick="toDel();">
						</td>
					</tr>
					<tr>
						<td height="5" valign="top"></td>
					</tr>
					<tr>
						<td height="28" valign="top">
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								   id="selectTable_content">
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
										${pager.page}/${pager.totalPage} <span onclick="nextPage()" class="pager" style="cursor: pointer">下一页</span> <span
												onclick="lastPage()" class="pager" style="cursor: pointer">末页</span>
										<select name="_pageNum" onchange="toUrl('_pageNum_up','_null');"
												id="_pageNum_up">
											<c:forEach begin="1" end="${pager.totalPage}" varStatus="sta">
												<option value="${sta.index }" <c:if test="${pager.page==sta.index }">selected="selected"</c:if>>第${sta.index }页</option>
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
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											   id="select_row">
											<tr>
												<td width="2%" height="28" align="center" background="image/select_title_title.jpg">
													<input type="checkbox" id="ids" name="checkbox" value="checkbox" onclick="toChange()">
												</td>
												<td width="18%" align="left"
													background="image/select_title_title.jpg"><strong>商品名称</strong></td>
												<td width="27%" align="center"
													background="image/select_title_title.jpg"><strong>商品编码</strong><strong></strong></td>
												<td width="12%" align="center"
													background="image/select_title_title.jpg"><strong>商品类别</strong></td>
												<td width="7%" align="center"
													background="image/select_title_title.jpg"><strong>型号</strong></td>
												<td width="7%" align="center"
													background="image/select_title_title.jpg"><strong>规格</strong></td>
												<td width="7%" align="center"
													background="image/select_title_title.jpg"><strong>尺寸</strong></td>
												<td width="11%" align="center"
													background="image/select_title_title.jpg"><strong>库存数量</strong></td>
												<td width="9%" align="center"
													background="image/select_title_title.jpg"><strong>单价</strong></td>
											</tr>
												<c:forEach items="${productList}" var="product">
													<tr class="select_content_bg">
														<td align="center">
															<input type="checkbox" name="product_id" value="${product.id}" onclick="changeNum()">
														</td>
														<td><a href="product/toUpdate?productId=${product.id}">${product.name}</a></td>
														<td align="center">${product.code}</td>
														<td align="center">${product.productTypeName}</td>
														<td align="center">${product.model}</td>
														<td align="center">${product.spec}</td>
														<td align="center">${product.productSize}</td>
														<td align="center">${product.stock}</td>
														<td align="center">${product.price}</td>
													</tr>
												</c:forEach>

											<tr>
												<td colspan="9">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td width="40%" height="19" bgcolor="#f2faff" style="font-size: 12px;">&nbsp;
										<img src="image/t2.gif" align="absmiddle" width="15" height="16"> 导出 | 选择条目:<span id="selected_num2">0</span>
									</td>
									<td width="3%" bgcolor="#f2faff" style="font-size: 12px;">&nbsp;</td>
									<td width="57%" bgcolor="#f2faff" align="right" style="font-size: 12px;">共${pager.totalRecord}条
										<span class="pager" onclick="firstPage();" style="cursor: pointer">首页</span>
										<span class="pager" onclick="prePage();" style="cursor: pointer">上一页</span>
										${pager.page}/${pager.totalPage} <span onclick="nextPage()" class="pager" style="cursor: pointer">下一页</span> <span
												onclick="lastPage()" class="pager" style="cursor: pointer">末页</span>
										<select name="_pageNum" onchange="toUrl('_pageNum_down','_null');" id="_pageNum_down">
											<c:forEach begin="1" end="${pager.totalPage}" varStatus="sta">
												<option value="${sta.index }" <c:if test="${pager.page==sta.index }">selected="selected"</c:if>>第${sta.index }页</option>
											</c:forEach>
										</select> <select name="_pageSize" onchange="toUrl('_null','_pageSize_down')" id="_pageSize_down">
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
										</select></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

			</td>
		</tr>
	</table>
</form>
</body>
</html>