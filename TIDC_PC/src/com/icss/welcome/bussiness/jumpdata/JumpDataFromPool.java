package com.icss.welcome.bussiness.jumpdata;

import java.util.List;

import com.icss.base.db.DBBeanBase;
import com.icss.welcome.bussiness.vo.PushJumpDataVO;


public class JumpDataFromPool {
	public List<PushJumpDataVO> getJumData(){
		DBBeanBase dbbean = new DBBeanBase();
		List<PushJumpDataVO> voList = null;
		try {
			voList = dbbean.executeQuery(getSql(), "com.icss.welcome.bussiness.vo.PushJumpDataVO");
        } catch (Exception e) {
	        e.printStackTrace();
        }finally{
        	dbbean.close();
        }
		return voList;
	}
	
	public void delData(String time){
		String sql = "delete from MA_T_JUMP_DATA_POOL ";
		if(time != null){
			sql += " where time <= '"+time+"' ";
		}
		DBBeanBase dbbean = new DBBeanBase();
		try{
			dbbean.executeUpdate(sql);
		}finally{
			dbbean.close();
		}
	}
	
	private String getSql(){
		StringBuffer sql = new StringBuffer();
		sql.append("select max(time) as time,INS_PROD_QTY,INS_SAL_QTY ,INS_STK_QTY ");
		sql.append(",COM_BUY_QTY ,COM_SAL_QTY ,COM_STK_QTY ,TRANSIT_QTY ");
		sql.append("from MA_T_JUMP_DATA_POOL group by INS_PROD_QTY ");
		sql.append(",INS_SAL_QTY ,INS_STK_QTY ,COM_BUY_QTY ,COM_SAL_QTY ");
		sql.append(",COM_STK_QTY ,TRANSIT_QTY order by time  fetch first 50 rows only with ur");
		return sql.toString();
		
	}
	
}
