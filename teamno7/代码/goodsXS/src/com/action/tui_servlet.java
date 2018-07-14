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
import com.orm.Ttui;
import com.orm.Txiaoshou;
import com.service.liuService;

public class tui_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		if(type.endsWith("tuiMana"))
		{
			tuiMana(req, res);
		}
		if(type.endsWith("tuiAdd"))
		{
			tuiAdd(req, res);
		}
		if(type.endsWith("tuiDel"))
		{
			tuiDel(req, res);
		}
	}
	
	
	public void tuiAdd(HttpServletRequest req,HttpServletResponse res)
	{
		String id=String.valueOf(new Date().getTime());
		int goods_id=Integer.parseInt(req.getParameter("goods_id"));
		String shijian=req.getParameter("shijian");
		float zongjia=Float.parseFloat(req.getParameter("zongjia"));
		
		String gukename=req.getParameter("gukename");
		String beizhu=req.getParameter("beizhu");
		String del="no";
		
		String sql="insert into t_tui values(?,?,?,?,?,?,?)";
		Object[] params={id,goods_id,shijian,zongjia,gukename,beizhu,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "tui?type=tuiMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void tuiDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="update t_tui set del='yes' where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "tui?type=tuiMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}

	public void tuiMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List tuiList=new ArrayList();
		String sql="select * from t_tui where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Ttui tui=new Ttui();
				
				tui.setId(rs.getString("id"));
				tui.setGoods_id(rs.getInt("goods_id"));
				tui.setShijian(rs.getString("shijian"));
				tui.setZongjia(rs.getFloat("zongjia"));
				
				tui.setGukename(rs.getString("gukename"));
				tui.setBeizhu(rs.getString("beizhu"));
				tui.setDel(rs.getString("del"));
				
				tui.setGoods_name(liuService.getGoodsName(rs.getInt("goods_id")));
				tuiList.add(tui);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("tuiList", tuiList);
		req.getRequestDispatcher("admin/tui/tuiMana.jsp").forward(req, res);
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
