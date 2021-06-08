package com.icss.tjfx.total.structure.vo;

/**
 * 前台展现汇总模型类
 * 
 * @author lcx
 * @since June 26, 2017
 * @version 1.0
 * 
 * */
public class ModelVO {

	private PriceClassPieVO priceClassPieVO;
	private SingleVO  singleVO;
	private SellNumBarVO sellNumBarVO;
	public PriceClassPieVO getPriceClassPieVO() {
		return priceClassPieVO;
	}
	public void setPriceClassPieVO(PriceClassPieVO priceClassPieVO) {
		this.priceClassPieVO = priceClassPieVO;
	}
	public SingleVO getSingleVO() {
		return singleVO;
	}
	public void setSingleVO(SingleVO singleVO) {
		this.singleVO = singleVO;
	}
	public SellNumBarVO getSellNumBarVO() {
		return sellNumBarVO;
	}
	public void setSellNumBarVO(SellNumBarVO sellNumBarVO) {
		this.sellNumBarVO = sellNumBarVO;
	}
	
}
