package com.icss.welcome.bussiness;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.icss.base.business.BaseBusiness;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.welcome.bussiness.jumpdata.ComJumpData;
import com.icss.welcome.bussiness.jumpdata.InsJumpData;
import com.icss.welcome.bussiness.jumpdata.TransitJumpData;
import com.icss.welcome.bussiness.map.ComMap;
import com.icss.welcome.bussiness.map.InsMap;
import com.icss.welcome.bussiness.vo.MapDataVO;
import com.icss.welcome.bussiness.vo.MapLineVO;
import com.icss.welcome.bussiness.vo.MapModelVO;
import com.icss.welcome.bussiness.vo.MapSDataVO;
import com.icss.welcome.bussiness.vo.PushJumpDataVO;
import com.icss.welcome.vo.WelcomMapModelVO;

public class WelcomeJumpNumDeal extends BaseBusiness{
	private static Log log = LogFactory.getLog(WelcomeJumpNumDeal.class);
	public HttpServletRequest handle(HttpServletRequest request) throws Exception {

		log.info("------欢迎页开始-------");
		WelcomMapModelVO data = new WelcomMapModelVO();
		//第一次蹦数数据
		log.info("第一次蹦数-----------------------");
    	ComJumpData cj = new ComJumpData();
    	InsJumpData ij = new InsJumpData();
    	TransitJumpData tj = new TransitJumpData();
    	PushJumpDataVO cjvo = new PushJumpDataVO();
    	PushJumpDataVO ijvo= new PushJumpDataVO();
    	PushJumpDataVO tjvo= new PushJumpDataVO();
    	PushJumpDataVO pjdvo= new PushJumpDataVO();
		cjvo = cj.getComJumpData("stma");
		ijvo = ij.getInsJumpData("stma");
		tjvo = tj.getTransitJumpData("stma");
		
    	pjdvo.setCOM_BUY_QTY(cjvo.getCOM_BUY_QTY());
    	pjdvo.setCOM_BUY_QTY_MONTH(cjvo.getCOM_BUY_QTY_MONTH());
    	pjdvo.setCOM_BUY_QTY_YEAR(cjvo.getCOM_BUY_QTY_YEAR());
    	
    	pjdvo.setCOM_SAL_QTY(cjvo.getCOM_SAL_QTY());
    	pjdvo.setCOM_SAL_QTY_MONTH(cjvo.getCOM_SAL_QTY_MONTH());
    	pjdvo.setCOM_SAL_QTY_YEAR(cjvo.getCOM_SAL_QTY_YEAR());
    	
    	pjdvo.setCOM_SALSUM_QTY(cjvo.getCOM_SALSUM_QTY());
    	pjdvo.setCOM_SALSUM_QTY_MONTH(cjvo.getCOM_SALSUM_QTY_MONTH());
    	pjdvo.setCOM_SALSUM_QTY_YEAR(cjvo.getCOM_SALSUM_QTY_YEAR());
    	
    	pjdvo.setCOM_DX(cjvo.getCOM_DX());
    	pjdvo.setCOM_JD(cjvo.getCOM_JD());
    	
    	pjdvo.setCOM_STK_QTY(cjvo.getCOM_STK_QTY());
    	
    	pjdvo.setINS_PROD_QTY(ijvo.getINS_PROD_QTY());
    	pjdvo.setINS_PROD_QTY_MONTH(ijvo.getINS_PROD_QTY_MONTH());
    	pjdvo.setINS_PROD_QTY_YEAR(ijvo.getINS_PROD_QTY_YEAR());
    	
    	pjdvo.setINS_SAL_QTY(ijvo.getINS_SAL_QTY());
    	pjdvo.setINS_SAL_QTY_MONTH(ijvo.getINS_SAL_QTY_MONTH());
    	pjdvo.setINS_SAL_QTY_YEAR(ijvo.getINS_SAL_QTY_YEAR());
    	
    	pjdvo.setTRANSIT_QTY(tjvo.getTRANSIT_QTY());
    	pjdvo.setINS_STK_QTY(ijvo.getINS_STK_QTY());
		
    	pjdvo.setINS_SALSUM_QTY(ijvo.getINS_SALSUM_QTY());
    	pjdvo.setINS_SALSUM_QTY_MONTH(ijvo.getINS_SALSUM_QTY_MONTH());
    	pjdvo.setINS_SALSUM_QTY_YEAR(ijvo.getINS_SALSUM_QTY_YEAR());
    	
    	pjdvo.setINS_DX(ijvo.getINS_DX());
    	pjdvo.setINS_JD(ijvo.getINS_JD());
    	
		data.setJumpNum(pjdvo);
		
		Gson gson = new Gson();
		String s = gson.toJson(data);
		
		/*log.info("返回数据："+s);*/
		log.info("==ajxa请求数据==");
		request.setAttribute("ajxa", "Y");
		request.setAttribute("jspName", "Welcome.jsp");
		request.setAttribute("result", s);
		
		return request;
	}
}
