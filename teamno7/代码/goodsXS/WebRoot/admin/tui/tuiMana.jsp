<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		<script type="text/javascript" src="<%=path %>/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=path %>/js/popup.js"></script>
        <script language="javascript">
           function tuiDel(id)
           {
               if(confirm('您确定删除吗？'))
               {
                   window.location.href="<%=path %>/tui?type=tuiDel&id="+id;
               }
           }
           
           function tuiAdd()
           {
                 var url="<%=path %>/admin/tui/tuiAdd.jsp";
				 window.location.href=url;
           }
           
           
           function check()
           {
               if(document.form1.shijian_sta.value=="")
               {
                  alert("开始时间不能空");
                  return false;
               }
               if(document.form1.shijian_end.value=="")
               {
                  alert("结束时间不能空");
                  return false;
               }
               return true;
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="11" background="<%=path %>/images/tbg.gif">&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="10%">商品</td>
					<td width="10%">时间</td>
					<td width="10%">总价</td>
					<td width="10%">顾客</td>
					<td width="10%">备注</td>
					<td width="10%">操作</td>
		        </tr>	
				<c:forEach items="${requestScope.tuiList}" var="tui">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${tui.goods_name}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${tui.shijian}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${tui.zongjia}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${tui.gukename}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${tui.beizhu}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<a href="#" onclick="tuiDel(${tui.id})" class="pn-loperator">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			
			<form action="<%=path %>/tui?type=tuiTongji" name="form1" method="post">
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td width="50">
			        <input type="button" value="添加" style="width: 80px;" onclick="tuiAdd()" />
			    </td>
			    <td></td>
			  </tr>
		    </table>
		    </form>
	</body>
</html>
