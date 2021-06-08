package com.icss.welcome.vo;

import java.util.List;

import com.icss.welcome.bussiness.vo.MapModelVO;
import com.icss.welcome.bussiness.vo.PushJumpDataVO;


public class WelcomMapModelVO {
	
    private List<MapModelVO> insmapdata;		//地图数据List 工业19家中烟，商业33个省
    private List<MapModelVO> commapdata;		//地图数据List 工业19家中烟，商业33个省
    private PushJumpDataVO jumpNum;
    
	public WelcomMapModelVO() {
		super();
	}

	public WelcomMapModelVO(List<MapModelVO> insmapdata,
			List<MapModelVO> commapdata, PushJumpDataVO jumpNum) {
		super();
		this.insmapdata = insmapdata;
		this.commapdata = commapdata;
		this.jumpNum = jumpNum;
	}

	public List<MapModelVO> getInsmapdata() {
		return insmapdata;
	}

	public void setInsmapdata(List<MapModelVO> insmapdata) {
		this.insmapdata = insmapdata;
	}

	public List<MapModelVO> getCommapdata() {
		return commapdata;
	}

	public void setCommapdata(List<MapModelVO> commapdata) {
		this.commapdata = commapdata;
	}

	public PushJumpDataVO getJumpNum() {
		return jumpNum;
	}

	public void setJumpNum(PushJumpDataVO jumpNum) {
		this.jumpNum = jumpNum;
	}

	@Override
	public String toString() {
		return "WelcomMapModelVO [insmapdata=" + insmapdata + ", commapdata="
				+ commapdata + ", jumpNum=" + jumpNum + "]";
	}


	
}
