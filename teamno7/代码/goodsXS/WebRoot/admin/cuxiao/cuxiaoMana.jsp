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
		
        <script language="javascript">
           function cuxiaoDel(id)
           {
               if(confirm('您确定删除吗？'))
               {
                   window.location.href="<%=path %>/cuxiao?type=cuxiaoDel&id="+id;
               }
           }
           
           function cuxiaoAdd()
           {
                 var url="<%=path %>/admin/cuxiao/cuxiaoAdd.jsp";
				 window.location.href=url;
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="11" background="<%=path %>/images/tbg.gif">&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="15%">开始时间</td>
					<td width="15%">结束时间</td>
					<td width="15%">促销商品</td>
					<td width="15%">原价</td>
					<td width="15%">特价</td>
					<td width="10%">操作</td>
		        </tr>	
				<c:forEach items="${requestScope.cuxiaoList}" var="cuxiao">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${cuxiao.shijian_sta}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${cuxiao.shijian_end}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${cuxiao.goods_name}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${cuxiao.yuanjia}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${cuxiao.tejia}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<form action="<%=path %>/admin/cuxiao/cuxiaoEditPre.jsp" name="formAdd" method="post">
						     <input type="button" value="删除" onclick="cuxiaoDel(${cuxiao.id})"/>
						</form>
					</td>
				</tr>
				</c:forEach>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td>
			      <input type="button" value="添加" style="width: 80px;" onclick="cuxiaoAdd()" />
			    </td>
			  </tr>
		    </table>
	</body>
</html>
