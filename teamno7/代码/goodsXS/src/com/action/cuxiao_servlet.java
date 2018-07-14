package com.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DB;
import com.orm.Tcuxiao;
import com.orm.Tgoods;
import com.service.liuService;

public class cuxiao_servlet extends HttpServlet
{ 
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
		String type=req.getParameter("type");
		
		if(type.endsWith("cuxiaoAdd"))
		{
			cuxiaoAdd(req, res);
		}
		if(type.endsWith("cuxiaoMana"))
		{
			cuxiaoMana(req, res);
		}
		if(type.endsWith("cuxiaoDel"))
		{
			cuxiaoDel(req, res);
		}
	}
	
	
	
	
	public void cuxiaoAdd(HttpServletRequest req,HttpServletResponse res)
	{
		
		String shijian_sta=req.getParameter("shijian_sta");
		String shijian_end=req.getParameter("shijian_end");
		int goods_id=Integer.parseInt(req.getParameter("goods_id"));
		String yuanjia=req.getParameter("yuanjia");
		String tejia=req.getParameter("tejia");
		String del="no";
		
		String sql="insert into t_cuxiao values(?,?,?,?,?,?)";
		Object[] params={shijian_sta,shijian_end,goods_id,yuanjia,tejia,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "cuxiao?type=cuxiaoMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	public void cuxiaoDel(HttpServletRequest req,HttpServletResponse res)
	{
		int id=Integer.parseInt(req.getParameter("id"));
		String sql="update t_cuxiao set del='yes' where id="+id;
		Object[] params={};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "cuxiao?type=cuxiaoMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	public void cuxiaoMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List cuxiaoList=new ArrayList();
		String sql="select * from t_cuxiao where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
                Tcuxiao cuxiao=new Tcuxiao();
				
				cuxiao.setId(rs.getInt("id"));
				cuxiao.setShijian_sta(rs.getString("shijian_sta"));
				cuxiao.setShijian_end(rs.getString("shijian_end"));
				cuxiao.setGoods_id(rs.getInt("goods_id"));
				cuxiao.setYuanjia(rs.getString("yuanjia"));
				cuxiao.setTejia(rs.getString("tejia"));
				cuxiao.setGoods_name(liuService.getGoodsName(rs.getInt("goods_id")));
				cuxiaoList.add(cuxiao);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("cuxiaoList", cuxiaoList);
		req.getRequestDispatcher("admin/cuxiao/cuxiaoMana.jsp").forward(req, res);
	}
	
	public void cuxiaoEdit(HttpServletRequest req,HttpServletResponse res)
	{
		String name=req.getParameter("name");
		String chandi=req.getParameter("chandi");
		String danwei=req.getParameter("danwei");
		String guige=req.getParameter("guige");
		String beizhu=req.getParameter("beizhu");
		String id=req.getParameter("id");
		String sql="update t_cuxiao set name=?,chandi=?,danwei=?,guige=?,beizhu=? where id="+id;
		Object[] params={name,chandi,danwei,guige,beizhu};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "cuxiao?type=cuxiaoMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	{
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try 
		{
		    dispatch.forward(request, response);
		    return;
		} 
		catch (ServletException e) 
		{
                    e.printStackTrace();
		} 
		catch (IOException e) 
		{
			
		    e.printStackTrace();
		}
	}
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
	}
	
	public void destroy() 
	{
		
	}
}
