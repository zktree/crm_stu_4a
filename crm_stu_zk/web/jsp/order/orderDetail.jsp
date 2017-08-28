<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <script type="text/javascript">
        function openProductWindow(){
            window.open("open/productList?MType=productList_openWindow","_blank",
                "width=740,height=380,toolbar=no,menubar=no,resizable=no,status=no,scrollbars=yes");
        }
        function sum(){
            var amount = $("#amount").val() * 1;
            var price = $("#price").val() * 1;
            var agio = $("#agio").val() * 1;

            if(agio > 100){
                alert("折扣不能大于100!");
                $("#agio").val("100");
                return false;
            }
            var sum = agio/100 * price * amount;
            $("#total").val(sum.toFixed(2)+"");
        }
        function submitCheck(){
            var total = $("#total").val();
            if(!isNaN(total)){
                document.getElementById("orderDetailForm").submit();
                window.opener.location = window.opener.location;
            }else{
                alert("数据格式错误！");
            }
            window.close();
        }
    </script>
    <title>销售单明细--新建</title>
</head>
<body>
<form name="orderDetailForm" method="post" action="order/orderDetail/addOrUpdate" id="orderDetailForm">
    <input type="hidden" name="edit_id" id="edit_id" value="${orderDetail.id}" />
    <input type="hidden" id="pId" name="pId" value="${pId}"/>
    <input type="hidden" name="orderId" value="${orderId}${orderDetail.orderId}"/>
    <table width="99%" border="0" cellspacing="0" cellpadding="0" id="index_main_table">
        <tr>
            <td height="26" width="100%"> <span style="color:#002450; font-size:13px; font-weight:bold;">　销售单明细--新建</span></td>
        </tr>
    </table>

    <table width="99%" border="0" cellspacing="0" cellpadding="0" id="index_content">
        <tr>
            <td height="12"></td>
        </tr>
        <tr>
            <td valign="middle" ><img src="image/s10.gif" onclick="submitCheck();" width="58" height="22"><img src="image/guanbi.gif" width="59" height="22"></td>
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
                                <td width="10%"><span style="color:#FF0000">产品名称:</span></td>
                                <td width="41%">
                                    <input type="text" name="pName" class="addCText" id="pName" value="${orderDetail.pName}">
                                    <img src="image/dakaichuangkou.gif" width="21" height="19" onclick="openProductWindow();">
                                </td>
                                <td width="7%" align="left">&nbsp;</td>
                                <td width="42%">&nbsp;</td>
                                <td width="0%">&nbsp;</td>
                            </tr>
                            <tr>
                                <td>规格:</td>
                                <td><input type="text" name="spec" id="spec" class="addCText" value="${orderDetail.spec}"></td>
                                <td align="left">单位:</td>
                                <td><input type="text" name="unit" class="addCText" id="unit" value="${orderDetail.unit}"></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>数量:</td>
                                <td><input type="text" name="amount" class="addCText" id="amount" value="${orderDetail.amount}" onkeyup="sum();"></td>
                                <td align="left">单价:</td>
                                <td><input type="text" name="price" class="addCText" id="price" value="${orderDetail.price}" onkeyup="sum();"></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>折扣:</td>
                                <td><input type="text" name="agio" class="addCText" id="agio" value="${orderDetail.agio}" onkeyup="sum();">
                                    %</td>
                                <td align="left">金额:</td>
                                <td><input type="text" name="total" class="addCText" id="total" value="${orderDetail.total}"></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>备注:</td>
                                <td colspan="3"><textarea name="remark" id="remark" class="addCText2"></textarea></td>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>

            </td>
        </tr>
        <tr>
            <td valign="middle" >&nbsp;</td>
        </tr>
    </table>
</form>
</body>
</html>