package com.icss.welcome.bussiness.jumpdata;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.welcome.bussiness.PushJumpData;
import com.icss.welcome.bussiness.jumpdata.poolswitch.Constant;
import com.icss.welcome.bussiness.jumpdata.poolswitch.PushFromPoolSwitch;
import com.icss.welcome.bussiness.vo.PushJumpDataVO;


public class JunpDataFromPoolJob implements Runnable {
	private static Log log  = LogFactory.getLog(JunpDataFromPoolJob.class);
	public void run() {
		try{
			
			String time = "";
			JumpDataFromPool pool = new JumpDataFromPool();
			while(PushFromPoolSwitch.getState()){
				List<PushJumpDataVO> voList = pool.getJumData();
				ListIterator<PushJumpDataVO> itr = voList.listIterator();
				while(itr.hasNext()){
					
					PushJumpDataVO vo = itr.next();
					if(vo == null){
						continue;
					}
					time = vo.getTime();
					pushDataToJsp(vo);
					TimeUnit.SECONDS.sleep(Constant.sleepTime);
				}
				pool.delData(time);
			}
		}catch (Exception e) {
			log.error(e);
		}
		
    }

	private void pushDataToJsp(PushJumpDataVO vo) {
	    PushJumpData.jumpDataMap.put("INS_PROD_QTY_MAX", Long.parseLong(vo.getINS_PROD_QTY()));
	    PushJumpData.jumpDataMap.put("INS_SAL_QTY_MAX", Long.parseLong(vo.getINS_SAL_QTY()));
	    PushJumpData.jumpDataMap.put("INS_STK_QTY_MAX", Long.parseLong(vo.getINS_STK_QTY()));
	    PushJumpData.jumpDataMap.put("COM_BUY_QTY_MAX", Long.parseLong(vo.getCOM_BUY_QTY()));
	    PushJumpData.jumpDataMap.put("COM_SAL_QTY_MAX", Long.parseLong(vo.getCOM_SAL_QTY()));
	    PushJumpData.jumpDataMap.put("COM_STK_QTY_MAX", Long.parseLong(vo.getCOM_STK_QTY()));
	    PushJumpData.jumpDataMap.put("TRANSIT_QTY_MAX", Long.parseLong(vo.getTRANSIT_QTY()));
    }
}
