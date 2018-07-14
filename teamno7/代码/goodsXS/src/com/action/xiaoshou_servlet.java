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
import com.orm.Txiaoshou;
import com.service.liuService;

public class xiaoshou_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		if(type.endsWith("xiaoshouMana"))
		{
			xiaoshouMana(req, res);
		}
		if(type.endsWith("xiaoshouAdd"))
		{
			xiaoshouAdd(req, res);
		}
		if(type.endsWith("xiaoshouDel"))
		{
			xiaoshouDel(req, res);
		}
	}
	
	
	public void xiaoshouAdd(HttpServletRequest req,HttpServletResponse res)
	{
		String id=String.valueOf(new Date().getTime());
		int goods_id=Integer.parseInt(req.getParameter("goods_id"));
		String shijian=req.getParameter("shijian");
		float zongjia=Float.parseFloat(req.getParameter("zongjia"));
		
		String zhifufangshi=req.getParameter("zhifufangshi");
		String gukename=req.getParameter("gukename");
		String beizhu=req.getParameter("beizhu");
		String del="no";
		
		String sql="insert into t_xiaoshou values(?,?,?,?,?,?,?,?)";
		Object[] params={id,goods_id,shijian,zongjia,zhifufangshi,gukename,beizhu,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "xiaoshou?type=xiaoshouMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void xiaoshouDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="update t_xiaoshou set del='yes' where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		req.setAttribute("path", "xiaoshou?type=xiaoshouMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}

	public void xiaoshouMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List xiaoshouList=new ArrayList();
		String sql="select * from t_xiaoshou where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Txiaoshou xiaoshou=new Txiaoshou();
				
				xiaoshou.setId(rs.getString("id"));
				xiaoshou.setGoods_id(rs.getInt("goods_id"));
				xiaoshou.setShijian(rs.getString("shijian"));
				xiaoshou.setZongjia(rs.getFloat("zongjia"));
				
				xiaoshou.setZhifufangshi(rs.getString("zhifufangshi"));
				xiaoshou.setGukename(rs.getString("gukename"));
				xiaoshou.setBeizhu(rs.getString("beizhu"));
				xiaoshou.setDel(rs.getString("del"));
				
				xiaoshou.setGoods_name(liuService.getGoodsName(rs.getInt("goods_id")));
				xiaoshouList.add(xiaoshou);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("xiaoshouList", xiaoshouList);
		req.getRequestDispatcher("admin/xiaoshou/xiaoshouMana.jsp").forward(req, res);
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
