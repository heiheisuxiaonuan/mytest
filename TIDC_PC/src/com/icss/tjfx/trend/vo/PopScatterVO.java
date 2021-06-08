package com.icss.tjfx.trend.vo;

import java.util.List;

/**
 * 走势图品牌散点图vo模型
 * 
 * @author lkt
 * @since June 26, 2017
 * @version 1.0
 * 
 * */
public class PopScatterVO {

	private String date;		    //时间
	private String x;               //x坐标
	private String y;               //y坐标
	private String value;           //值
	private String name;            //品牌名称
	private String code;            //品牌编码
	private List dateList;          //存放时间date
	public PopScatterVO() {
		super();
	}
	@Override
	public String toString() {
		return "PopScatterVO [date=" + date + ", x=" + x + ", y=" + y
				+ ", value=" + value + ", name=" + name + ", code=" + code
				+ ", dateList=" + dateList + ", getDate()=" + getDate()
				+ ", getX()=" + getX() + ", getY()=" + getY() + ", getValue()="
				+ getValue() + ", getName()=" + getName() + ", getCode()="
				+ getCode() + ", getDateList()=" + getDateList()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List getDateList() {
		return dateList;
	}
	public void setDateList(List dateList) {
		this.dateList = dateList;
	}
	public PopScatterVO(String date, String x, String y, String value,
			String name, String code, List dateList) {
		super();
		this.date = date;
		this.x = x;
		this.y = y;
		this.value = value;
		this.name = name;
		this.code = code;
		this.dateList = dateList;
	}
}
