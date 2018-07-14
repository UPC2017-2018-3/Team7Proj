package com.orm;

public class Tcuxiao
{
	private int id;
	private String shijian_sta;
	private String shijian_end;
	private int goods_id;
	private String yuanjia;
	private String tejia;
	private String del;
	
	private String goods_name;
	
	public String getDel()
	{
		return del;
	}
	public void setDel(String del)
	{
		this.del = del;
	}
	
	public int getGoods_id()
	{
		return goods_id;
	}
	public void setGoods_id(int goods_id)
	{
		this.goods_id = goods_id;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getShijian_end()
	{
		return shijian_end;
	}
	public void setShijian_end(String shijian_end)
	{
		this.shijian_end = shijian_end;
	}
	
	public String getGoods_name()
	{
		return goods_name;
	}
	public void setGoods_name(String goods_name)
	{
		this.goods_name = goods_name;
	}
	public String getShijian_sta()
	{
		return shijian_sta;
	}
	public void setShijian_sta(String shijian_sta)
	{
		this.shijian_sta = shijian_sta;
	}
	public String getTejia()
	{
		return tejia;
	}
	public void setTejia(String tejia)
	{
		this.tejia = tejia;
	}
	public String getYuanjia()
	{
		return yuanjia;
	}
	public void setYuanjia(String yuanjia)
	{
		this.yuanjia = yuanjia;
	}

}
