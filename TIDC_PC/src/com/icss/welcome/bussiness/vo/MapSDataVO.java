package com.icss.welcome.bussiness.vo;

public class MapSDataVO {
	//地图特殊数据（只含有名字）
	private String name;		//地图数据格式  NAME

	public MapSDataVO() {
		super();
	}

	public MapSDataVO(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MapSDataVO [name=" + name + "]";
	}
	
}
