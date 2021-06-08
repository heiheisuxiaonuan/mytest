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

public class WelcomeDeal extends BaseBusiness{
	private static Log log = LogFactory.getLog(WelcomeDeal.class);
	public HttpServletRequest handle(HttpServletRequest request) throws Exception {

		log.info("------欢迎页开始-------");
		WelcomMapModelVO data = new WelcomMapModelVO();
		//添加商业地图和工业地图数据
		log.info("流向数据-----------------------");
		ComMap commap = new ComMap();
		InsMap insmap = new InsMap();
		List<MapModelVO> modelList = insmap.getInsMapData("dcma");
		List<MapModelVO> modelComList = commap.getComMapData("dcma");
		data.setInsmapdata(modelList);
		data.setCommapdata(modelComList);
		
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
