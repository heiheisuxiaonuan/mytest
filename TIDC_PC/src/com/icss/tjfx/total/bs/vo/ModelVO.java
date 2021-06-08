package com.icss.tjfx.total.bs.vo;

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
	private List<SellUpTopThreeVO> list;
	private SellUpNumVO sellUpNumVO;
	public List<SellUpTopThreeVO> getList() {
		return list;
	}
	public void setList(List<SellUpTopThreeVO> list) {
		this.list = list;
	}
	public SellUpNumVO getSellUpNumVO() {
		return sellUpNumVO;
	}
	public void setSellUpNumVO(SellUpNumVO sellUpNumVO) {
		this.sellUpNumVO = sellUpNumVO;
	}
	
	
}
