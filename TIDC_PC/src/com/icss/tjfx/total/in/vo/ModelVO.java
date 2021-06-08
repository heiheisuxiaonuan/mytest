package com.icss.tjfx.total.in.vo;

import java.util.List;

/**
 * 前台展现汇总模型类
 * 
 * @author lcx
 * @since June 26, 2017
 * @version 1.0
 * 
 * */
public class ModelVO {
	private SingleVO singleVO;
	private ProduceNumBarVO produceNumBarVO;
	private List<OutProvinceVO> outProvinceVOList;
	public SingleVO getSingleVO() {
		return singleVO;
	}
	public void setSingleVO(SingleVO singleVO) {
		this.singleVO = singleVO;
	}
	public ProduceNumBarVO getProduceNumBarVO() {
		return produceNumBarVO;
	}
	public void setProduceNumBarVO(ProduceNumBarVO produceNumBarVO) {
		this.produceNumBarVO = produceNumBarVO;
	}
	public List<OutProvinceVO> getOutProvinceVOList() {
		return outProvinceVOList;
	}
	public void setOutProvinceVOList(List<OutProvinceVO> outProvinceVOList) {
		this.outProvinceVOList = outProvinceVOList;
	}
	
	
	
}
