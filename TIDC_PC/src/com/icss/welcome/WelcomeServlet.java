package com.icss.welcome;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.base.servlet.BaseServlet;
import com.icss.welcome.bussiness.PushJumpData;
import com.icss.welcome.bussiness.jumpdata.ComJumpData;
import com.icss.welcome.bussiness.jumpdata.InsJumpData;
import com.icss.welcome.bussiness.jumpdata.JumpDataJob;
import com.icss.welcome.bussiness.jumpdata.TransitJumpData;
import com.icss.welcome.bussiness.vo.PushJumpDataVO;

public class WelcomeServlet extends BaseServlet {

	private static Log log = LogFactory.getLog(WelcomeServlet.class);

//	@Override
//	public void init() throws ServletException {
//		// 第一次取 old和max用于后来的判断
//		try {
//			ComJumpData cj = new ComJumpData();
//			InsJumpData ij = new InsJumpData();
//			TransitJumpData tj = new TransitJumpData();
//			PushJumpDataVO cjvo = new PushJumpDataVO();
//			PushJumpDataVO ijvo = new PushJumpDataVO();
//			PushJumpDataVO tjvo = new PushJumpDataVO();
//			try {
//				cjvo = cj.getComJumpData("stma");
//				ijvo = ij.getInsJumpData("stma");
//				tjvo = tj.getTransitJumpData("stma");
//			} catch (Exception e) {
//				log.error("执行实时条数sql执行异常", e);
//			}
//			PushJumpData.jumpDataMap.put("INS_PROD_QTY_OLD", Long.parseLong(ijvo.getINS_PROD_QTY()));
//			PushJumpData.jumpDataMap.put("INS_PROD_QTY_MAX", Long.parseLong(ijvo.getINS_PROD_QTY()));
//
//			PushJumpData.jumpDataMap.put("INS_SAL_QTY_OLD", Long.parseLong(ijvo.getINS_SAL_QTY()));
//			PushJumpData.jumpDataMap.put("INS_SAL_QTY_MAX", Long.parseLong(ijvo.getINS_SAL_QTY()));
//
//			PushJumpData.jumpDataMap.put("INS_STK_QTY_OLD", Long.parseLong(ijvo.getINS_STK_QTY()));
//			PushJumpData.jumpDataMap.put("INS_STK_QTY_MAX", Long.parseLong(ijvo.getINS_STK_QTY()));
//
//			PushJumpData.jumpDataMap.put("COM_BUY_QTY_OLD", Long.parseLong(cjvo.getCOM_BUY_QTY()));
//			PushJumpData.jumpDataMap.put("COM_BUY_QTY_MAX", Long.parseLong(cjvo.getCOM_BUY_QTY()));
//
//			PushJumpData.jumpDataMap.put("COM_SAL_QTY_OLD", Long.parseLong(cjvo.getCOM_SAL_QTY()));
//			PushJumpData.jumpDataMap.put("COM_SAL_QTY_MAX", Long.parseLong(cjvo.getCOM_SAL_QTY()));
//
//			PushJumpData.jumpDataMap.put("COM_STK_QTY_OLD", Long.parseLong(cjvo.getCOM_STK_QTY()));
//			PushJumpData.jumpDataMap.put("COM_STK_QTY_MAX", Long.parseLong(cjvo.getCOM_STK_QTY()));
//
//			PushJumpData.jumpDataMap.put("TRANSIT_QTY_OLD", Long.parseLong(tjvo.getTRANSIT_QTY()));
//			PushJumpData.jumpDataMap.put("TRANSIT_QTY_MAX", Long.parseLong(tjvo.getTRANSIT_QTY()));
//			
//			PushJumpData.jumpDataMap.put("INS_PROD_QTY_MONTH_OLD", Long.parseLong(ijvo.getINS_PROD_QTY_MONTH()));
//			PushJumpData.jumpDataMap.put("INS_PROD_QTY_MONTH_MAX", Long.parseLong(ijvo.getINS_PROD_QTY_MONTH()));
//			
//			PushJumpData.jumpDataMap.put("INS_PROD_QTY_YEAR_OLD", Long.parseLong(ijvo.getINS_PROD_QTY_YEAR()));
//			PushJumpData.jumpDataMap.put("INS_PROD_QTY_YEAR_MAX", Long.parseLong(ijvo.getINS_PROD_QTY_YEAR()));
//			
//			PushJumpData.jumpDataMap.put("INS_SAL_QTY_MONTH_OLD", Long.parseLong(ijvo.getINS_SAL_QTY_MONTH()));
//			PushJumpData.jumpDataMap.put("INS_SAL_QTY_MONTH_MAX", Long.parseLong(ijvo.getINS_SAL_QTY_MONTH()));
//			
//			PushJumpData.jumpDataMap.put("INS_SAL_QTY_YEAR_OLD", Long.parseLong(ijvo.getINS_SAL_QTY_YEAR()));
//			PushJumpData.jumpDataMap.put("INS_SAL_QTY_YEAR_MAX", Long.parseLong(ijvo.getINS_SAL_QTY_YEAR()));
//			
//			PushJumpData.jumpDataMap.put("INS_SALSUM_QTY_OLD", Long.parseLong(ijvo.getINS_SALSUM_QTY()));
//			PushJumpData.jumpDataMap.put("INS_SALSUM_QTY_MAX", Long.parseLong(ijvo.getINS_SALSUM_QTY()));
//			
//			PushJumpData.jumpDataMap.put("INS_SALSUM_QTY_MONTH_OLD", Long.parseLong(ijvo.getINS_SALSUM_QTY_MONTH()));
//			PushJumpData.jumpDataMap.put("INS_SALSUM_QTY_MONTH_MAX", Long.parseLong(ijvo.getINS_SALSUM_QTY_MONTH()));
//			
//			PushJumpData.jumpDataMap.put("INS_SALSUM_QTY_YEAR_OLD", Long.parseLong(ijvo.getINS_SALSUM_QTY_YEAR()));
//			PushJumpData.jumpDataMap.put("INS_SALSUM_QTY_YEAR_MAX", Long.parseLong(ijvo.getINS_SALSUM_QTY_YEAR()));
//			
//			PushJumpData.jumpDataMap.put("INS_DX_OLD", Double.parseDouble(ijvo.getINS_DX()));
//			PushJumpData.jumpDataMap.put("INS_DX_MAX", Double.parseDouble(ijvo.getINS_DX()));
//			
//			PushJumpData.jumpDataMap.put("INS_JD_OLD", Double.parseDouble(ijvo.getINS_JD()));
//			PushJumpData.jumpDataMap.put("INS_JD_MAX", Double.parseDouble(ijvo.getINS_JD()));
//			
//			PushJumpData.jumpDataMap.put("COM_BUY_QTY_MONTH_OLD", Long.parseLong(cjvo.getCOM_BUY_QTY_MONTH()));
//			PushJumpData.jumpDataMap.put("COM_BUY_QTY_MONTH_MAX", Long.parseLong(cjvo.getCOM_BUY_QTY_MONTH()));
//			
//			PushJumpData.jumpDataMap.put("COM_BUY_QTY_YEAR_OLD", Long.parseLong(cjvo.getCOM_BUY_QTY_YEAR()));
//			PushJumpData.jumpDataMap.put("COM_BUY_QTY_YEAR_MAX", Long.parseLong(cjvo.getCOM_BUY_QTY_YEAR()));
//			
//			PushJumpData.jumpDataMap.put("COM_SAL_QTY_MONTH_OLD", Long.parseLong(cjvo.getCOM_SAL_QTY_MONTH()));
//			PushJumpData.jumpDataMap.put("COM_SAL_QTY_MONTH_MAX", Long.parseLong(cjvo.getCOM_SAL_QTY_MONTH()));
//			
//			PushJumpData.jumpDataMap.put("COM_SAL_QTY_YEAR_OLD", Long.parseLong(cjvo.getCOM_SAL_QTY_YEAR()));
//			PushJumpData.jumpDataMap.put("COM_SAL_QTY_YEAR_MAX", Long.parseLong(cjvo.getCOM_SAL_QTY_YEAR()));
//			
//			PushJumpData.jumpDataMap.put("COM_SALSUM_QTY_OLD", Long.parseLong(cjvo.getCOM_SALSUM_QTY()));
//			PushJumpData.jumpDataMap.put("COM_SALSUM_QTY_MAX", Long.parseLong(cjvo.getCOM_SALSUM_QTY()));
//			
//			PushJumpData.jumpDataMap.put("COM_SALSUM_QTY_MONTH_OLD", Long.parseLong(cjvo.getCOM_SALSUM_QTY_MONTH()));
//			PushJumpData.jumpDataMap.put("COM_SALSUM_QTY_MONTH_MAX", Long.parseLong(cjvo.getCOM_SALSUM_QTY_MONTH()));
//			
//			PushJumpData.jumpDataMap.put("COM_SALSUM_QTY_YEAR_OLD", Long.parseLong(cjvo.getCOM_SALSUM_QTY_YEAR()));
//			PushJumpData.jumpDataMap.put("COM_SALSUM_QTY_YEAR_MAX", Long.parseLong(cjvo.getCOM_SALSUM_QTY_YEAR()));
//			
//			PushJumpData.jumpDataMap.put("COM_DX_OLD", Double.parseDouble(cjvo.getCOM_DX()));
//			PushJumpData.jumpDataMap.put("COM_DX_MAX", Double.parseDouble(cjvo.getCOM_DX()));
//			
//			PushJumpData.jumpDataMap.put("COM_JD_OLD", Double.parseDouble(cjvo.getCOM_JD()));
//			PushJumpData.jumpDataMap.put("COM_JD_MAX", Double.parseDouble(cjvo.getCOM_JD()));
//
//			new Thread(new JumpDataJob()).start();
//		} catch (Exception e) {
//			log.error(e);
//		}
//
//	}

	public void performTask(HttpServletRequest request, HttpServletResponse response) {
		try {
			log.info("==welcome请求数据==");
			System.out.println("==welcome请求数据==");
			request.setCharacterEncoding("UTF-8");
			super.deal(request, response, "com.icss.welcome.bussiness", "/jsp/welcome");
		} catch (Exception e) {
			log.error("发生异常", e);
			handleError(request, response, e);
		}
	}
}
