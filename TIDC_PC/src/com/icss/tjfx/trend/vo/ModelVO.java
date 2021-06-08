package com.icss.tjfx.trend.vo;

import java.util.List;

/**
 * 前台展现汇总模型类
 * 
 * @author lkt
 * @since June 26, 2017
 * @version 1.0
 * 
 * */
public class ModelVO {

	private List<String> yearList;                           //年份list
	private List<TaxLineVO> slList;                           //税利返回结果
	private List<TaxLineLayerVO> slLayerList;                 //税利弹出返回结果
	private List<PopScatterVO> ppList;                        //品牌返回结果
	private List<PopScatterLayerVO> ppLayerList;              //品牌弹出返回结果
	private TaxMaxMin taxMaxMin;                           	  //税利最大最小值
	public ModelVO() {
		super();
	}
	public ModelVO(List<TaxLineVO> slList, List<TaxLineLayerVO> slLayerList,
			List<PopScatterVO> ppList, List<PopScatterLayerVO> ppLayerList,
			TaxMaxMin taxMaxMin) {
		super();
		this.slList = slList;
		this.slLayerList = slLayerList;
		this.ppList = ppList;
		this.ppLayerList = ppLayerList;
		this.taxMaxMin = taxMaxMin;
	}
	public List<TaxLineVO> getSlList() {
		return slList;
	}
	public void setSlList(List<TaxLineVO> slList) {
		this.slList = slList;
	}
	public List<TaxLineLayerVO> getSlLayerList() {
		return slLayerList;
	}
	public void setSlLayerList(List<TaxLineLayerVO> slLayerList) {
		this.slLayerList = slLayerList;
	}
	public List<PopScatterVO> getPpList() {
		return ppList;
	}
	public void setPpList(List<PopScatterVO> ppList) {
		this.ppList = ppList;
	}
	public List<PopScatterLayerVO> getPpLayerList() {
		return ppLayerList;
	}
	public void setPpLayerList(List<PopScatterLayerVO> ppLayerList) {
		this.ppLayerList = ppLayerList;
	}
	public TaxMaxMin getTaxMaxMin() {
		return taxMaxMin;
	}
	public void setTaxMaxMin(TaxMaxMin taxMaxMin) {
		this.taxMaxMin = taxMaxMin;
	}
	
	public List<String> getYearList() {
		return yearList;
	}
	public void setYearList(List<String> yearList) {
		this.yearList = yearList;
	}
	@Override
	public String toString() {
		return "ModelVO [ppLayerList=" + ppLayerList + ", ppList=" + ppList
				+ ", slLayerList=" + slLayerList + ", slList=" + slList
				+ ", taxMaxMin=" + taxMaxMin + ", yearList=" + yearList + "]";
	}
}
