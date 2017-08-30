<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

    <script type="text/javascript">

        $(function(){
            //判断是新增还是修改
            var orderId="${order.id}";
            var seller = "${order.seller}"
            if(orderId==""){//新增
            }else{//修改
                $('select[name="seller"]').val(seller);
            }
        });




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
                $('#orderDate').val('<fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd "/>');
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
            if('' != '${order.createTime}'){
                $('#createTime').val('<fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>');
            }
            else if('' != '${c_time}'){
                $('#createTime').val('<fmt:formatDate value="${c_time}" pattern="yyyy-MM-dd HH:mm:ss"/>');
            }else{
                $('#createTime').val('点击显示时间列表');
            }

            if('' != '${u_time}'){
                $('#updateTime').val('<fmt:formatDate value="${u_time}" pattern="yyyy-MM-dd HH:mm:ss"/>');
            }else{
                $('#updateTime').val('点击显示时间列表');
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

                <%--if('${createTime}' != "点击显示时间列表"){--%>
                    <%--$("#createTime").val('');--%>
                <%--}--%>

            }
            if('$("#orderDate")' == "点击显示时间列表"){
                $("#orderDate").val('');
            }
            if ($('#createTime').val()=="点击显示时间列表"){
                $('#createTime').val("");
            }
            if ($('#updateTime').val()=="点击显示时间列表"){
                $('#updateTime').val("");
            }


            document.getElementById("orderForm").submit();
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
            window.open("open/companyList?MType=companyList_openWindow","_blank",
                "width=740,height=380,toolbar=no,menubar=no,resizable=no,status=no,scrollbars=yes");
        }
        function openCompanyLinkmanWindow(){
            var o = document.getElementById("cId");
            if(o.value != ""){
                window.open("open/linkmanList?cId="+o.value+"&MType=linkManOpenWindow","_blank",
                    "width=740,height=380,toolbar=no,menubar=no,resizable=no,status=no,scrollbars=yes");
            }else{
                alert("请选择客户!");
            }

        }
    </script>
</head>
<body>
<form name="orderForm" method="post" id="orderForm" action="order/addOrUpdate">
    <input type="hidden" name="edit_id" id="edit_id" value="${order.id}" />
    <table width="99%" border="0" cellspacing="0" cellpadding="0" id="index_main_table">
        <tr>
            <td height="26" width="100%"> <span style="color:#002450; font-size:13px; font-weight:bold;">　销售单--新建</span></td>
        </tr>
    </table>

    <table width="99%" border="0" cellspacing="0" cellpadding="0" id="index_content">
        <tr>
            <td height="12"></td>
        </tr>
        <tr>
            <td valign="middle" ><img src="image/s10.gif" width="58" height="22" style="cursor: pointer;" onclick="submitForm();">
                <img src="image/s8.gif" width="59" height="22" onclick=history.go(-1)>
                <span style="font-size:12px;color:#009900;">保存后可新建明细</span></td>
        </tr>
        <tr>
            <td id="errorInfo" style="font-size: 12px; color: #FF0000;">${mess}</td>
        </tr>
        <tr>
            <td valign="middle" ><table width="100%" border="0" cellspacing="0" cellpadding="0" id="index_mxain_div1">
                <tr>
                    <td height="21" valign="top" background="image/index_main_div_titleBg.gif"><img style="margin-left:5px;" src="image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">基本信息</span></td>
                </tr>
                <tr>
                    <td height="56" align="left" valign="top" bgcolor="#f7fbfc">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" id="selectTable">
                            <tr>
                                <td width="10%"><span style="color:#FF0000">销售单号:</span></td>
                                <td width="41%"><input type="text" name="code" class="addCText" value="${todayStr}${order.code}"></td>
                                <td width="7%" align="left"><span style="color:#FF0000">开单日期:</span></td>
                                <td width="42%"><input type="text" name="orderDate" value="${order.orderDate}" id="orderDate" class="addCText"></td>
                                <td width="0%">&nbsp;</td>
                            </tr>
                            <tr>
                                <td><span style="color:#FF0000">客户名称:</span></td>
                                <td>
                                    <input type="text" name="cName" id="cName" value="${order.cName}" class="addCText">
                                    <input type="hidden" name="cId" value="${order.cId}" id="cId"/>
                                    <%--<input type="hidden" name="cName" value="${order.cId}"/>--%>
                                    <img src="image/dakaichuangkou.gif" width="21" height="19" onclick="openCompanyWindow()">
                                </td>
                                <td align="left">业务员:</td>
                                <td><select name="seller" class="addCText">
                                            <option></option>
                                            <c:forEach items="${salesmans}" var="salesman">
                                                <option value="${salesman.name}">${salesman.name}</option>
                                            </c:forEach>

                                    <%--<s:iterator value="#request.userList" id="s_user">--%>
                                        <%--<s:if test="#request.edit_id==null">--%>
                                            <%--<s:if test="#s_user.name==#session.user.name">--%>
                                                <%--<option value="${s_user.name }" selected="selected">${s_user.name }</option>--%>
                                            <%--</s:if>--%>
                                            <%--<s:else>--%>
                                                <%--<option value="${s_user.name }">${s_user.name }</option>--%>
                                            <%--</s:else>--%>
                                        <%--</s:if>--%>
                                        <%--<s:else>--%>
                                            <%--<s:if test="#request.order.seller == #s_user.name">--%>
                                                <%--<option value="${s_user.name }" selected="selected">${s_user.name }</option>--%>
                                            <%--</s:if>--%>
                                            <%--<s:else>--%>
                                                <%--<option value="${s_user.name }">${s_user.name }</option>--%>
                                            <%--</s:else>--%>
                                        <%--</s:else>--%>

                                    <%--</s:iterator>--%>
                                </select>
                                </td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>联系人:</td>
                                <td><input type="text" name="linkman" value="${order.linkman}" class="addCText" id="linkman">
                                    <img src="image/dakaichuangkou.gif" width="21" height="19" onclick="openCompanyLinkmanWindow();"></td>
                                <td align="left">联系电话:</td>
                                <td><input type="text" name="telephone" class="addCText" value="${order.telephone}"></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>送货地址:</td>
                                <td colspan="3"><input type="text" name="address" class="addCText" value="${order.address}"></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>备注:</td>
                                <td colspan="3"><textarea name="remark" class="addCText2">${order.remark}</textarea></td>
                                <td>&nbsp;</td>
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
                                    <td><input name="custom1" value="${product.custom1}" type="text" class="addCText" id="custom1"></td>
                                    <td width="66" align="left">自定义2:</td>
                                    <td width="40%"><input name="custom2" value="${product.custom2}" type="text" class="addCText" id="custom2"></td>
                                    <td width="1">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td>自定义3:</td>
                                    <td><input name="custom3" value="${product.custom3}" type="text" class="addCText" id="custom3"></td>
                                    <td align="left">自定义4:</td>
                                    <td><input name="custom4" value="${product.custom4}" type="text" class="addCText" id="custom4"></td>
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
                                    <td width="40%"><input name="createTime" id="createTime" value="${c_time}${product.createTime}" type="text" class="addCText" id="createTime" readOnly="readOnly"></td>
                                    <td width="2%">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td>修改人:</td>
                                    <td><input name="updater" type="text" class="addCText" value="${user.name}" id="updater" readOnly="readOnly"></td>
                                    <td align="left">修改日期:</td>
                                    <td><input name="updateTime" type="text" value='${u_time}' class="addCText" id="updateTime" readOnly="readOnly"></td>
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
        <tr>
            <td valign="middle" ><img src="image/s10.gif" width="58" height="22"><img src="image/s8.gif" width="59" height="22"></td>
        </tr>
        <tr>
            <td valign="middle" >&nbsp;</td>
        </tr>
    </table>
</form>
</body>
</html>