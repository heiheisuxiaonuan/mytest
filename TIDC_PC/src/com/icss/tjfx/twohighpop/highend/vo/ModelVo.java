package com.icss.tjfx.twohighpop.highend.vo;

import java.util.List;

public class ModelVo {
	private List<TowHighCharsVO> towHighCharsVOList;
	private TowHighCharsVO towHighCharsVO;
	private TowHighCharsVO towHighCharsVOHigh;

	public void setTowHighCharsVO(TowHighCharsVO towHighCharsVO) {
		this.towHighCharsVO = towHighCharsVO;
	}

	public TowHighCharsVO getTowHighCharsVO() {
		return towHighCharsVO;
	}

	public void setTowHighCharsVOList(List<TowHighCharsVO> towHighCharsVOList) {
		this.towHighCharsVOList = towHighCharsVOList;
	}

	public List<TowHighCharsVO> getTowHighCharsVOList() {
		return towHighCharsVOList;
	}

	public void setTowHighCharsVOHigh(TowHighCharsVO towHighCharsVOHigh) {
		this.towHighCharsVOHigh = towHighCharsVOHigh;
	}

	public TowHighCharsVO getTowHighCharsVOHigh() {
		return towHighCharsVOHigh;
	}
}
