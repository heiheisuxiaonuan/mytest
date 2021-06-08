package com.icss.welcome.bussiness.vo;

import java.util.List;

public class MapModelVO {
	private String typeName; // 存放那个省或中烟的名字
	private List<MapLineVO> mapLine; // 地图标线数据
	private List<MapDataVO> mapPoint; // 地图标记数据
	private String pronum1; // 產量
	private String outNumBs; // 銷量

	public MapModelVO() {
		super();
	}

	public MapModelVO(String typeName, List<MapLineVO> mapLine, List<MapDataVO> mapPoint, String pronum1,
			String outNumBs) {
		super();
		this.typeName = typeName;
		this.mapLine = mapLine;
		this.mapPoint = mapPoint;
		this.pronum1 = pronum1;
		this.outNumBs = outNumBs;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<MapLineVO> getMapLine() {
		return mapLine;
	}

	public void setMapLine(List<MapLineVO> mapLine) {
		this.mapLine = mapLine;
	}

	public List<MapDataVO> getMapPoint() {
		return mapPoint;
	}

	public void setMapPoint(List<MapDataVO> mapPoint) {
		this.mapPoint = mapPoint;
	}

	public String getOutNumBs() {
		return outNumBs;
	}

	public void setOutNumBs(String outNumBs) {
		this.outNumBs = outNumBs;
	}

	public String getPronum1() {
		return pronum1;
	}

	public void setPronum1(String pronum1) {
		this.pronum1 = pronum1;
	}

	@Override
	public String toString() {
		return "MapModelVO [typeName=" + typeName + ", mapLine=" + mapLine + ", mapPoint=" + mapPoint + ", pronum1="
				+ pronum1 + ", outNumBs=" + outNumBs + "]";
	}

}
