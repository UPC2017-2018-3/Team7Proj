package com.service;

import java.sql.ResultSet;

import com.dao.DB;
import com.orm.Tcatelog;

public class liuService
{
	public static String getCatelogName(int id)
	{
		String catelog_name="";
		
		String sql="select * from t_catelog where id="+id;
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			rs.next();
			catelog_name=rs.getString("name");
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		return catelog_name;
	}
	public static String getGoodsName(int id)
	{
		String name="";
		
		String sql="select * from t_goods where id="+id;
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			rs.next();
			name=rs.getString("name");
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		return name;
	}

}
