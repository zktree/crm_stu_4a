<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="css_js/jquery/jquery-1.3.2.min.js"></script>
    <link href="css_js/index.css" rel="stylesheet" type="text/css"></link>
    <link href="css_js/general.css" rel="stylesheet" type="text/css"></link>
    <link href="css_js/calendar.css" rel="stylesheet" type="text/css"></link>
    <script src="css_js/popup.js" type="text/javascript"></script>
    <script src="css_js/jquery.form.js" type="text/javascript"></script>
    <script src="css_js/cal.js" type="text/javascript"></script>
    <title>Insert title here</title>
    <style type="text/css">
        #productTable{
            border-collapse:collapse;

        }
        #productTable td{
            border:1px solid #cccccc;
            text-align:center;
            font-size:12px;
            color:#444444;
        }
        .productInput1,.productInput2{
            width:79px;
            height:18px;
            background-color:#eef3f8;
            border:none;
            border-bottom:1px #ccc solid;
        }
        .productInput2{
            width:50px;
        }
        .productInput21 {	width:79px;
            height:18px;
            background-color:#eef3f8;
            border:none;
            border-bottom:1px #ccc solid;
        }

    </style>

    <script type="text/javascript">

        jQuery(document).ready(function () {
            $('#time1').simpleDatepicker({ startdate: 1960, enddate: 2060 });
            $('#time2').simpleDatepicker({ startdate: 1960, enddate: 2060 });
            $('#orderDate').simpleDatepicker({ startdate: 1960, enddate: 2060 });
            //是否已经有了数据

            if('' != '${order.time1}'){
                $('#time1').val('${order.time1}');
            }else{
                $('#time1').val('点击显示时间列表');
            }
            if('' != '${order.time2}'){
                $('#time2').val('${order.time2}');
            }else{
                $('#time2').val('点击显示时间列表');
            }
            if('' != '${order.orderDate}'){
                $('#orderDate').val('${order.orderDate}');
            }else{
                $('#orderDate').val('点击显示时间列表');
            }
            //判断状态显示时间颜色

            if($('#time1').val()!="点击显示时间列表"){
                $('#time1').removeClass("addTimeInit");
                $('#time1').addClass("addTimeClick");
            }
            if($('#time2').val()!="点击显示时间列表"){
                $('#time2').removeClass("addTimeInit");
                $('#time2').addClass("addTimeClick");
            }
            if('${order.createTime}' != ""){
                $("#createTime").val('<s:date name="#request.order.createTime" format="yyyy-MM-dd" nice="false" />');
            }
            if('${c_time}' != ""){
                $("#updateTime").val('<s:date name="#request.c_time" format="yyyy-MM-dd" nice="false" />');
            }
        });
        jQuery(document).ready(function () {
            if('${order.creater}' != ""){
                $("#creater").val('${order.creater}');
            }else{
                $("#creater").val('${user.name}');
            }

        });
    </script>

    <script type="text/javascript">
        function submitForm(){

            if($('#time1').val()=="点击显示时间列表"){
                $('#time1').val("");
            }
            if($('#time2').val()=="点击显示时间列表"){
                $('#time2').val("");

                if('${orderDate}' == "点击显示时间列表"){
                    $("#orderDate").val('');
                }
                document.getElementById("orderForm").submit();
            }
        }

        function getPinyinCode(){
            var str = $("#name").val();
            if(str != ""){
                var queryString = {name:str};
                $.ajax({
                    type:"post",
                    url:"getPinyinCode.action",
                    data:queryString,
                    dataType:"json",
                    success: function(msg) {
                        $("#pycode").val(msg.name);
                    }
                });
            }
        }
        function openCompanyWindow(){
            window.open("load4Visit.action?MType=companyList_openWindow","_blank",
                "width=740,height=380,toolbar=no,menubar=no,resizable=no,status=no,scrollbars=yes");
        }
        function openCompanyLinkmanWindow(){
            var o = document.getElementById("CId");
            if(o.value != ""){
                window.open("load4LinkManMain.action?CId="+o.value+"&MType=linkManOpenWindow","_blank",
                    "width=740,height=380,toolbar=no,menubar=no,resizable=no,status=no,scrollbars=yes");
            }else{
                alert("请选择客户!");
            }

        }
        function openOrderDetailWindow(){
            window.open("loadOrderDetailPage.action?orderId=${edit_id}","_blank",
                "width=740,height=380,toolbar=no,menubar=no,resizable=no,status=no,scrollbars=yes");
        }
        function sum1(){
            var o = document.getElementsByName("detail_total");
            var sum = 0;
            for(var i = 0; i < o.length; i++){
                sum += o[i].value * 1;
            }
            document.getElementById("total").value = sum.toFixed(2);
            alert(document.getElementById("total").value);
            document.getElementById("agio_total").value = sum.toFixed(2);
        }
        function toSum(){
            var o = document.getElementsByName("detail_total");
            var sum = 0;
            for(var i = 0; i < o.length; i++){
                sum += o[i].value * 1;
            }
            document.getElementById("total").value = sum.toFixed(2);
            document.getElementById("agio_total").value = sum.toFixed(2);
        }
        function toAgio(){
            var total = document.getElementById("total").value;
            var agio = document.getElementById("agio").value;
            if(!isNaN(total) && !isNaN(agio)){
                document.getElementById("agio_total").value = (total - agio).toFixed(2);
            }
            else{
                alert("数据格式有误!");
            }
        }
        //用户订单明细的商品列表中的数据自动计算
        function detailSum(spec_id,unit_id,amount_id,price_id,agio_id,total_id,borderDetail_id){
            var _amount = $("#"+amount_id).val() * 1;

            var _price = $("#"+price_id).val() * 1;
            var _agio = $("#"+agio_id).val() * 1;
            var _spec = $("#"+spec_id).val();
            var _unit = $("#"+unit_id).val();

            if(_agio > 100){
                alert("折扣不能大于100!");
                $("#agio").val("100");
                return false;
            }
            var sum = _agio/100 * _price * _amount;
            $("#"+total_id).val(sum.toFixed(2));
            var queryString = {id:borderDetail_id,spec:_spec,unit:_unit,amount:_amount,price:_price,agio:_agio,total:sum};

            $.ajax({
                type:"post",
                url:"updateOrderDetail.action",
                data:queryString,
                dataType:"json",
                success: function(msg) {

                }
            });

            toSum();
            toAgio();

        }

        function openWindow4ToDraft(){
            window.open("auditReason.jsp","_blank",
                "width=740,height=380,toolbar=no,menubar=no,resizable=no,status=no,scrollbars=yes");
        }
    </script>
</head>
<body onload="toSum();">
<form name="orderForm" method="post" id="orderForm" action="orderAdd.action">
    <input type="hidden" name="auditReason" value="${order.auditReason}" id="auditReason"/>
    <input type="hidden" name="edit_id" value="${edit_id }"/>
    <table width="99%" border="0" cellspacing="0" cellpadding="0" id="index_main_table">
        <tr>
            <td height="26" width="100%"> <span style="color:#002450; font-size:13px; font-weight:bold;">　销售单--编辑</span></td>
        </tr>
    </table>

    <table width="99%" border="0" cellspacing="0" cellpadding="0" id="index_content">
        <tr>
            <td height="12"></td>
        </tr>
        <tr>
            <td valign="middle" >
                <s:if test="#request.orderDetailSize==0">
                    <img src="image/s10.gif" width="58" height="22" style="cursor: pointer;" onclick="submitForm();"><img src="image/s7.gif" width="59" height="22"><img src="image/s8.gif" width="59" height="22"><span style="font-size:12px;color:#009900;"></span>
                </s:if>
                <s:elseif test="#request.order.total==null">
                    <img src="image/s10.gif" width="58" height="22" style="cursor: pointer;" onclick="submitForm();"><img src="image/s7.gif" width="59" height="22"><img src="image/s8.gif" width="59" height="22"><span style="font-size:12px;color:#009900;"></span>
                </s:elseif>
                <s:elseif test="#request.order.status=='草稿'">
                    <img src="image/s10.gif" width="58" height="22" style="cursor: pointer;" onclick="submitForm();"><img src="image/tijiao.gif" onclick="javascript:window.location.href='orderSubmit.action?edit_id=${edit_id}'"><img src="image/s8.gif" width="59" height="22"><span style="font-size:12px;color:#009900;"></span>
                </s:elseif>
                <s:elseif test="#request.order.status=='待审核'">
                    <img src="image/shenhe.gif" onclick="javascript:window.location.href='auditingPass.action?edit_id=${edit_id}'"><img src="image/bohui.gif" onclick="openWindow4ToDraft();"><img src="image/s8.gif" width="59" height="22"><span style="font-size:12px;color:#009900;"></span>
                </s:elseif>
                <s:else>
                    <img src="image/s8.gif" width="59" height="22"><span style="font-size:12px;color:#009900;"></span>
                </s:else>
            </td>
        </tr>
        <tr>
            <td id="errorInfo" style="font-size:12px;color:red;"><s:actionmessage /></td>
        </tr>
        <tr>
            <td valign="middle" ><table width="100%" border="0" cellspacing="0" cellpadding="0" id="index_main_div1">
                <tr>
                    <td height="21" valign="top" background="image/index_main_div_titleBg.gif"><img style="margin-left:5px;" src="image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">基本信息</span></td>
                </tr>
                <tr>
                    <td height="56" align="left" valign="top" bgcolor="#f7fbfc">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" id="selectTable">
                            <tr>
                                <td width="10%"><span style="color:#FF0000">销售单号:</span></td>
                                <td width="41%"><input type="text" name="code" class="addCText" value="${order.code}"></td>
                                <td width="7%" align="left"><span style="color:#FF0000">开单日期:</span></td>
                                <td width="42%"><input type="text" name="orderDate" value="${order.orderDate}" id="orderDate" class="addCText"></td>
                                <td width="0%">&nbsp;</td>
                            </tr>
                            <tr>
                                <td><span style="color:#FF0000">客户名称:</span></td>
                                <td><input type="text" name="CName" class="addCText" id="companyName"  value="${order.CName}">
                                    <input type="hidden" name="CId" id="CId">
                                    <input type="hidden" name="companyName" id="CName">
                                    <img src="image/dakaichuangkou.gif" width="21" height="19" onclick="openCompanyWindow();"></td>
                                <td align="left">业务员:</td>
                                <td><select name="seller" class="addCText">
                                    <s:iterator value="#request.userList" id="s_user">
                                        <s:if test="#request.edit_id==null">
                                            <s:if test="#s_user.name==#session.user.name">
                                                <option value="${s_user.name }" selected="selected">${s_user.name }</option>
                                            </s:if>
                                            <s:else>
                                                <option value="${s_user.name }">${s_user.name }</option>
                                            </s:else>
                                        </s:if>
                                        <s:else>
                                            <s:if test="#request.order.seller == #s_user.name">
                                                <option value="${s_user.name }" selected="selected">${s_user.name }</option>
                                            </s:if>
                                            <s:else>
                                                <option value="${s_user.name }">${s_user.name }</option>
                                            </s:else>
                                        </s:else>

                                    </s:iterator>
                                </select></td>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>

                <table width="100%" border="0" cellspacing="0" cellpadding="0" id="index_main_div1">
                    <tr>
                        <td height="21" valign="top" background="image/index_main_div_titleBg.gif"><img style="margin-left:5px;" src="image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">商品信息</span></td>
                    </tr>
                    <tr>
                        <td height="56" align="left" valign="top" bgcolor="#f7fbfc">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" id="selectTable">
                                <tr>
                                    <td colspan="3" style="padding:2px;"><img src="image/s3.gif" width="62" height="22" onclick="openOrderDetailWindow();"><img src="image/daoru.gif" width="59" height="22"></td>
                                </tr>
                                <tr>
                                    <td colspan="3" align="center" style="padding:2px;">

                                        <table width="100%" cellspacing="0" cellpadding="0" id="productTable">
                                            <tr>
                                                <td width="31%">产品名称</td>
                                                <td width="11%">规格</td>
                                                <td width="11%">单位</td>
                                                <td width="12%">数量</td>
                                                <td width="11%">单价</td>
                                                <td width="10%">折扣</td>
                                                <td width="8%">金额</td>
                                                <td width="6%">操作</td>
                                            </tr>








                                            <s:iterator value="#request.order.orderDetail" id="borderDetail">
                                                <tr>
                                                    <td>${borderDetail.PName}</td>
                                                    <td><input type="text" name="detail_spec" class="productInput1" value="${borderDetail.spec}" id="detail_spec${borderDetail.id}"></td>
                                                    <td><input type="text" name="detail_unit" class="productInput1" value="${borderDetail.unit}" id="detail_unit${borderDetail.id}"></td>
                                                    <td><input type="text" name="detail_amount" class="productInput1" value="${borderDetail.amount}" id="detail_amount${borderDetail.id}" onkeyup="detailSum('detail_spec${borderDetail.id}','detail_unit${borderDetail.id}','detail_amount${borderDetail.id}','detail_price${borderDetail.id}','detail_agio${borderDetail.id}','detail_total${borderDetail.id}','${borderDetail.id}');"></td>
                                                    <td><input type="text" name="detail_price" class="productInput1"  value="${borderDetail.price}" id="detail_price${borderDetail.id}" onkeyup="detailSum('detail_spec${borderDetail.id}','detail_unit${borderDetail.id}','detail_amount${borderDetail.id}','detail_price${borderDetail.id}','detail_agio${borderDetail.id}','detail_total${borderDetail.id}','${borderDetail.id}');"></td>
                                                    <td><input type="text" name="detail_agio" class="productInput2" value="${borderDetail.agio}" id="detail_agio${borderDetail.id}" onkeyup="detailSum('detail_spec${borderDetail.id}','detail_unit${borderDetail.id}','detail_amount${borderDetail.id}','detail_price${borderDetail.id}','detail_agio${borderDetail.id}','detail_total${borderDetail.id}','${borderDetail.id}');">
                                                        %</td>
                                                    <td><input type="text" name="detail_total" class="productInput1"  value="${borderDetail.total}" id="detail_total${borderDetail.id}"></td>
                                                    <td><a href="deleteOrderDetail.action?id=${borderDetail.id}" class="normal">删除</a></td>
                                                </tr>
                                            </s:iterator>












                                        </table>							</td>
                                </tr>
                                <tr>
                                    <td width="31%">&nbsp;</td>
                                    <td width="63%" align="right">合计:　　　

                                        <input type="text" name="total" class="productInput1" id="total">
                                        　　　
                                        整单打折:　　　

                                        <input type="text" name="agio" class="productInput1" value="0.0" id="agio" onkeyup="toAgio();">
                                        　　　
                                        应收金额:　　　

                                        <input type="text" name="agioTotal" class="productInput1" id="agio_total"></td>
                                    <td width="6%" align="right">&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>



                <table width="100%" border="0" cellspacing="0" cellpadding="0" id="index_main_div1">
                    <tr>
                        <td height="21" valign="top" background="image/index_main_div_titleBg.gif"><img style="margin-left:5px;" src="image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">驳回原因</span></td>
                    </tr>
                    <tr>
                        <td height="56" align="left" valign="top" bgcolor="#f7fbfc">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" id="selectTable">

                                <tr>
                                    <td width="66">驳回原因:</td>
                                    <td colspan="4"><textarea name="remark" class="addCText2" readonly="readonly">${order.auditReason}</textarea></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>



                <table width="100%" border="0" cellspacing="0" cellpadding="0" id="index_main_div1">
                    <tr>
                        <td height="21" valign="top" background="image/index_main_div_titleBg.gif"><img style="margin-left:5px;" src="image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">送货地址</span></td>
                    </tr>
                    <tr>
                        <td height="56" align="left" valign="top" bgcolor="#f7fbfc">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" id="selectTable">
                                <tr>
                                    <td width="66">联系人:</td>
                                    <td><input type="text" name="linkman" class="addCText" value="${order.linkman}" id="linkman">
                                        <img src="image/dakaichuangkou.gif" width="21" height="19" onclick="openCompanyLinkmanWindow();"></td>
                                    <td width="66" align="left">联系电话:</td>
                                    <td width="40%"><input type="text" name="telephone" class="addCText" value="${order.telephone}"></td>
                                    <td width="1">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td>送货地址:</td>
                                    <td colspan="4"><input type="text" name="address" class="addCText1" value="${order.address}"></td>
                                </tr>
                                <tr>
                                    <td>备注:</td>
                                    <td colspan="4"><textarea name="remark" class="addCText2">${order.remark}</textarea></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>

                <table width="100%" border="0" cellspacing="0" cellpadding="0" id="index_main_div1">
                    <tr>
                        <td height="21" valign="top" background="image/index_main_div_titleBg.gif"><img style="margin-left:5px;" src="image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">自定义信息</span></td>
                    </tr>
                    <tr>
                        <td height="56" align="left" valign="top" bgcolor="#f7fbfc">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" id="selectTable">
                                <tr>
                                    <td width="66">自定义1:</td>
                                    <td><input name="custom1" value="${order.custom1}" type="text" class="addCText" id="custom1"></td>
                                    <td width="66" align="left">自定义2:</td>
                                    <td width="40%"><input name="custom2" value="${order.custom2}" type="text" class="addCText" id="custom2"></td>
                                    <td width="1">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td>自定义3:</td>
                                    <td><input name="custom3" value="${order.custom3}" type="text" class="addCText" id="custom3"></td>
                                    <td align="left">自定义4:</td>
                                    <td><input name="custom4" value="${order.custom4}" type="text" class="addCText" id="custom4"></td>
                                    <td width="1"></td>
                                </tr>
                                <tr>
                                    <td>自定选项1:</td>
                                    <td><select name="choice1" class="addCText" id="product_choice1">
                                        <option value="1">---</option>
                                        <s:iterator value="#request.sDictionaryDetailList" id="sdDetail">
                                            <s:if test="#sdDetail.SDictionaryClass.id == 31">
                                                <s:if test="#sdDetail.value == #request.product.choice1">
                                                    <option value="${sdDetail.value}" selected='selected'>${sdDetail.value}</option>
                                                </s:if>
                                                <s:else>
                                                    <option value="${sdDetail.value}">${sdDetail.value}</option>
                                                </s:else>
                                            </s:if>
                                        </s:iterator>
                                    </select> <img src="image/s11.gif" onclick="loadPopup('product_choice1')">
                                    </td>
                                    <td align="left">自定选项2:</td>
                                    <td><select name="choice2" class="addCText" id="product_choice2">
                                        <option value="1">---</option>
                                        <s:iterator value="#request.sDictionaryDetailList" id="sdDetail">
                                            <s:if test="#sdDetail.SDictionaryClass.id == 32">
                                                <s:if test="#sdDetail.value == #request.product.choice2">
                                                    <option value="${sdDetail.value}" selected='selected'>${sdDetail.value}</option>
                                                </s:if>
                                                <s:else>
                                                    <option value="${sdDetail.value}">${sdDetail.value}</option>
                                                </s:else>
                                            </s:if>
                                        </s:iterator>
                                    </select>
                                        <img src="image/s11.gif" onclick="loadPopup('product_choice2')"></td>
                                    <td width="1"></td>
                                </tr>
                                <tr>
                                    <td>自定选项3:</td>
                                    <td><select name="choice3" class="addCText" id="product_choice3">
                                        <option value="1">---</option>
                                        <s:iterator value="#request.sDictionaryDetailList" id="sdDetail">
                                            <s:if test="#sdDetail.SDictionaryClass.id == 33">
                                                <s:if test="#sdDetail.value == #request.product.choice3">
                                                    <option value="${sdDetail.value}" selected='selected'>${sdDetail.value}</option>
                                                </s:if>
                                                <s:else>
                                                    <option value="${sdDetail.value}">${sdDetail.value}</option>
                                                </s:else>
                                            </s:if>
                                        </s:iterator>
                                    </select>
                                        <img src="image/s11.gif" onclick="loadPopup('product_choice3')"></td>
                                    <td align="left">自定选项4</td>
                                    <td><select name="choice4" class="addCText" id="product_choice4">
                                        <option value="1">---</option>
                                        <s:iterator value="#request.sDictionaryDetailList" id="sdDetail">
                                            <s:if test="#sdDetail.SDictionaryClass.id == 34">
                                                <s:if test="#sdDetail.value == #request.product.choice4">
                                                    <option value="${sdDetail.value}" selected='selected'>${sdDetail.value}</option>
                                                </s:if>
                                                <s:else>
                                                    <option value="${sdDetail.value}">${sdDetail.value}</option>
                                                </s:else>
                                            </s:if>
                                        </s:iterator>
                                    </select>
                                        <img src="image/s11.gif" onclick="loadPopup('product_choice4')"></td>
                                    <td width="1"></td>
                                </tr>
                                <tr>
                                    <td>自定时间1:</td>
                                    <td><input type="text" name="time1"  id="time1"
                                               class="addTimeInit" value="点击显示时间列表" readonly="readonly">
                                    </td>
                                    <td align="left">自定时间2:</td>
                                    <td><input type="text" name="time2"  id="time2"
                                               class="addTimeInit" value="点击显示时间列表" readonly="readonly">
                                    </td>
                                    <td width="1"></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>


                <table width="100%" border="0" cellspacing="0" cellpadding="0" id="index_main_div1">
                    <tr>
                        <td height="21" valign="top" background="image/index_main_div_titleBg.gif"><img style="margin-left:5px;" src="image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">其他信息</span></td>
                    </tr>
                    <tr>
                        <td height="56" align="left" valign="top" bgcolor="#f7fbfc">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" id="selectTable">
                                <tr>
                                    <td width="7%">创建人:</td>
                                    <td width="42%"><input name="creater" type="text" class="addCText" id="creater" readOnly="readOnly">
                                    </td>
                                    <td width="9%" align="left">创建日期:</td>
                                    <td width="40%"><input name="createTime" id="createTime" value="${c_time}${order.createTime}" type="text" class="addCText" id="createTime" readOnly="readOnly"></td>
                                    <td width="2%">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td>修改人:</td>
                                    <td><input name="updater" type="text" value="${user.name }" class="addCText" id="updater" readOnly="readOnly"></td>
                                    <td align="left">修改日期:</td>
                                    <td><input name="updateTime" type="text" value='' class="addCText" id="updateTime" readOnly="readOnly"></td>
                                    <td>&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>	</td>
        </tr>
        <tr>
            <td valign="middle" >&nbsp;</td>
        </tr>
    </table>
</form>
</body>
</html>