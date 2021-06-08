package com.icss.welcome.bussiness.jumpdata;

import java.util.concurrent.TimeUnit;

import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.welcome.bussiness.PushJumpData;
import com.icss.welcome.bussiness.jumpdata.poolswitch.PushToPoolSwitch;
import com.icss.welcome.bussiness.vo.PushJumpDataVO;

public class JumpDataJob implements Runnable {
	//每10秒查找一下数据库，给最大数赋值
	private static Log log  = LogFactory.getLog(JumpDataJob.class);
	
	public void run() {

		while (true) {
			try {
				TimeUnit.SECONDS.sleep(10);
				
		    	ComJumpData cj = new ComJumpData();
		    	InsJumpData ij = new InsJumpData();
		    	TransitJumpData tj = new TransitJumpData();
		    	PushJumpDataVO cjvo = new PushJumpDataVO();
		    	PushJumpDataVO ijvo= new PushJumpDataVO();
		    	PushJumpDataVO tjvo= new PushJumpDataVO();
		    	try {
		    		cjvo = cj.getComJumpData("stma");
		    		ijvo = ij.getInsJumpData("stma");
		    		tjvo = tj.getTransitJumpData("stma");
				} catch (Exception e) {
					log.error("执行实时条数sql执行异常",e);
				}	
		    	
		    	if(PushToPoolSwitch.getState()){
		    		pushDataToPool(cjvo, ijvo, tjvo);
		    	}else{
		    		pushDataToJsp(cjvo, ijvo, tjvo);
		    	}
		    	
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	private void pushDataToJsp(PushJumpDataVO cjvo, PushJumpDataVO ijvo, PushJumpDataVO tjvo) {
	    PushJumpData.jumpDataMap.put("INS_PROD_QTY_MAX", Long.parseLong(ijvo.getINS_PROD_QTY()));
	    PushJumpData.jumpDataMap.put("INS_SAL_QTY_MAX", Long.parseLong(ijvo.getINS_SAL_QTY()));
	    PushJumpData.jumpDataMap.put("INS_STK_QTY_MAX", Long.parseLong(ijvo.getINS_STK_QTY()));
	    PushJumpData.jumpDataMap.put("COM_BUY_QTY_MAX", Long.parseLong(cjvo.getCOM_BUY_QTY()));
	    PushJumpData.jumpDataMap.put("COM_SAL_QTY_MAX", Long.parseLong(cjvo.getCOM_SAL_QTY()));
	    PushJumpData.jumpDataMap.put("COM_STK_QTY_MAX", Long.parseLong(cjvo.getCOM_STK_QTY()));
	    PushJumpData.jumpDataMap.put("TRANSIT_QTY_MAX", Long.parseLong(tjvo.getTRANSIT_QTY()));
		PushJumpData.jumpDataMap.put("INS_PROD_QTY_MONTH_MAX", Long.parseLong(ijvo.getINS_PROD_QTY_MONTH()));
		PushJumpData.jumpDataMap.put("INS_PROD_QTY_YEAR_MAX", Long.parseLong(ijvo.getINS_PROD_QTY_YEAR()));
		PushJumpData.jumpDataMap.put("INS_SAL_QTY_MONTH_MAX", Long.parseLong(ijvo.getINS_SAL_QTY_MONTH()));
		PushJumpData.jumpDataMap.put("INS_SAL_QTY_YEAR_MAX", Long.parseLong(ijvo.getINS_SAL_QTY_YEAR()));
		PushJumpData.jumpDataMap.put("INS_SALSUM_QTY_MAX", Long.parseLong(ijvo.getINS_SALSUM_QTY()));
		PushJumpData.jumpDataMap.put("INS_SALSUM_QTY_MONTH_MAX", Long.parseLong(ijvo.getINS_SALSUM_QTY_MONTH()));
		PushJumpData.jumpDataMap.put("INS_SALSUM_QTY_YEAR_MAX", Long.parseLong(ijvo.getINS_SALSUM_QTY_YEAR()));
		PushJumpData.jumpDataMap.put("INS_DX_MAX", Double.parseDouble(ijvo.getINS_DX()));
		PushJumpData.jumpDataMap.put("INS_JD_MAX", Double.parseDouble(ijvo.getINS_JD()));
		PushJumpData.jumpDataMap.put("COM_BUY_QTY_MONTH_MAX", Long.parseLong(cjvo.getCOM_BUY_QTY_MONTH()));
		PushJumpData.jumpDataMap.put("COM_BUY_QTY_YEAR_MAX", Long.parseLong(cjvo.getCOM_BUY_QTY_YEAR()));
		PushJumpData.jumpDataMap.put("COM_SAL_QTY_MONTH_MAX", Long.parseLong(cjvo.getCOM_SAL_QTY_MONTH()));
		PushJumpData.jumpDataMap.put("COM_SAL_QTY_YEAR_MAX", Long.parseLong(cjvo.getCOM_SAL_QTY_YEAR()));
		PushJumpData.jumpDataMap.put("COM_SALSUM_QTY_MAX", Long.parseLong(cjvo.getCOM_SALSUM_QTY()));
		PushJumpData.jumpDataMap.put("COM_SALSUM_QTY_MONTH_MAX", Long.parseLong(cjvo.getCOM_SALSUM_QTY_MONTH()));
		PushJumpData.jumpDataMap.put("COM_SALSUM_QTY_YEAR_MAX", Long.parseLong(cjvo.getCOM_SALSUM_QTY_YEAR()));
		PushJumpData.jumpDataMap.put("COM_DX_MAX", Double.parseDouble(cjvo.getCOM_DX()));
		PushJumpData.jumpDataMap.put("COM_JD_MAX", Double.parseDouble(cjvo.getCOM_JD()));
    }
	
	private void pushDataToPool(PushJumpDataVO cjvo, PushJumpDataVO ijvo, PushJumpDataVO tjvo) {
		
		PushDataPool pool = new PushDataPool();
		try {
	        pool.pushDataToPool(cjvo, ijvo, tjvo);
        } catch (Exception e) {
	        e.printStackTrace();
        }
		
    }

	
	
}
