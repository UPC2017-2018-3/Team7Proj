package com.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DB;
import com.orm.TAdmin;
import com.orm.Tcatelog;
import com.orm.Thuan;
import com.orm.Txiaoshou;
import com.service.liuService;

public class huan_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		if(type.endsWith("huanMana"))
		{
			huanMana(req, res);
		}
		if(type.endsWith("huanAdd"))
		{
			huanAdd(req, res);
		}
		if(type.endsWith("huanDel"))
		{
			huanDel(req, res);
		}
	}
	
	
	public void huanAdd(HttpServletRequest req,HttpServletResponse res)
	{
		String id=String.valueOf(new Date().getTime());
		int goods_id=Integer.parseInt(req.getParameter("goods_id"));
		String shijian=req.getParameter("shijian");
		float zongjia=Float.parseFloat(req.getParameter("zongjia"));
		
		String gukename=req.getParameter("gukename");
		String beizhu=req.getParameter("beizhu");
		String del="no";
		
		String sql="insert into t_huan values(?,?,?,?,?,?,?)";
		Object[] params={id,goods_id,shijian,zongjia,gukename,beizhu,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "huan?type=huanMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void huanDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="update t_huan set del='yes' where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "huan?type=huanMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}

	public void huanMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List huanList=new ArrayList();
		String sql="select * from t_huan where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Thuan huan=new Thuan();
				
				huan.setId(rs.getString("id"));
				huan.setGoods_id(rs.getInt("goods_id"));
				huan.setShijian(rs.getString("shijian"));
				huan.setZongjia(rs.getFloat("zongjia"));
				
				huan.setGukename(rs.getString("gukename"));
				huan.setBeizhu(rs.getString("beizhu"));
				huan.setDel(rs.getString("del"));
				
				huan.setGoods_name(liuService.getGoodsName(rs.getInt("goods_id")));
				huanList.add(huan);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("huanList", huanList);
		req.getRequestDispatcher("admin/huan/huanMana.jsp").forward(req, res);
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
