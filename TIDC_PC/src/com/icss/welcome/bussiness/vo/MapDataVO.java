package com.icss.welcome.bussiness.vo;

public class MapDataVO {
	//地图标准数据格式，标注的格式也是这个格式
    private String name;		//地图数据格式  NAME
    private String value;		//地图数据格式  VALUE
	public MapDataVO() {
		super();
	}
	public MapDataVO(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "MapDataVO [name=" + name + ", value=" + value + "]";
	}

}
