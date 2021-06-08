package com.icss.welcome.bussiness.vo;

public class MapLineVO {
	//地图表现数据格式
	
	private MapSDataVO mapsdata;	//只有一个名字
	private MapDataVO mapdata;		//有名字，有值
	public MapLineVO() {
		super();
	}
	public MapLineVO(MapSDataVO mapsdata, MapDataVO mapdata) {
		super();
		this.mapsdata = mapsdata;
		this.mapdata = mapdata;
	}
	public MapSDataVO getMapsdata() {
		return mapsdata;
	}
	public void setMapsdata(MapSDataVO mapsdata) {
		this.mapsdata = mapsdata;
	}
	public MapDataVO getMapdata() {
		return mapdata;
	}
	public void setMapdata(MapDataVO mapdata) {
		this.mapdata = mapdata;
	}
	@Override
	public String toString() {
		return "MapLineVO [mapsdata=" + mapsdata + ", mapdata=" + mapdata + "]";
	}
	
	
	
}
