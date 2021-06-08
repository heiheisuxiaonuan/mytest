package com.icss.welcome.bussiness.jumpdata;

import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.welcome.bussiness.PushJumpData;
import com.icss.welcome.bussiness.vo.PushJumpDataVO;


public class PushDataPool {
	private static Log log = LogFactory.getLog(PushDataPool.class);
	public void pushDataToPool(PushJumpDataVO cjvo, PushJumpDataVO ijvo, PushJumpDataVO tjvo) throws Exception{
		DBBeanBase dbbean = new DBBeanBase();

		String sql = "";
		try {
			sql = getSql(cjvo, ijvo, tjvo);
			log.debug(sql);
			dbbean.executeUpdate(sql);
		} catch (Exception e) {
			log.error("执行sql执行异常",e);
		    throw new Exception("数据库插入异常："+sql);
		}finally{
			dbbean.close();
		}
		
	}
	
	private String getSql(PushJumpDataVO cjvo, PushJumpDataVO ijvo, PushJumpDataVO tjvo){
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO MA_T_JUMP_DATA_POOL (INS_PROD_QTY, INS_SAL_QTY, INS_STK_QTY, COM_BUY_QTY, COM_SAL_QTY, COM_STK_QTY, TRANSIT_QTY) " +
				"VALUES ("+Long.parseLong(ijvo.getINS_PROD_QTY())+", "+Long.parseLong(ijvo.getINS_SAL_QTY())+", "+Long.parseLong(ijvo.getINS_STK_QTY())+"," +
						" "+Long.parseLong(cjvo.getCOM_BUY_QTY())+", "+Long.parseLong(cjvo.getCOM_SAL_QTY())+", "+Long.parseLong(cjvo.getCOM_STK_QTY())+", "+Long.parseLong(tjvo.getTRANSIT_QTY())+")");
		
		return sql.toString();
	}
}
