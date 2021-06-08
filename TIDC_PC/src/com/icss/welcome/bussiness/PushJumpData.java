package com.icss.welcome.bussiness;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;

import com.google.gson.Gson;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.welcome.bussiness.jumpdata.poolswitch.Constant;
import com.icss.welcome.bussiness.vo.PushJumpDataVO;

public class PushJumpData extends EventPullSource implements Serializable {

	private static Log log = LogFactory.getLog(PushJumpData.class);
	// 推数部分：判断推数的值
	public static Map<String, Object> jumpDataMap = new HashMap<String, Object>();
	static {
		PushJumpData.jumpDataMap.put("INS_PROD_QTY_OLD", 0L);
		PushJumpData.jumpDataMap.put("INS_PROD_QTY_MAX", 0L);

		PushJumpData.jumpDataMap.put("INS_SAL_QTY_OLD", 0L);
		PushJumpData.jumpDataMap.put("INS_SAL_QTY_MAX", 0L);

		PushJumpData.jumpDataMap.put("INS_STK_QTY_OLD", 0L);
		PushJumpData.jumpDataMap.put("INS_STK_QTY_MAX", 0L);

		PushJumpData.jumpDataMap.put("COM_BUY_QTY_OLD", 0L);
		PushJumpData.jumpDataMap.put("COM_BUY_QTY_MAX", 0L);

		PushJumpData.jumpDataMap.put("COM_SAL_QTY_OLD", 0L);
		PushJumpData.jumpDataMap.put("COM_SAL_QTY_MAX", 0L);

		PushJumpData.jumpDataMap.put("COM_STK_QTY_OLD", 0L);
		PushJumpData.jumpDataMap.put("COM_STK_QTY_MAX", 0L);

		PushJumpData.jumpDataMap.put("TRANSIT_QTY_OLD", 0L);
		PushJumpData.jumpDataMap.put("TRANSIT_QTY_MAX", 0L);

		PushJumpData.jumpDataMap.put("INS_PROD_QTY_MONTH_OLD", 0L);
		PushJumpData.jumpDataMap.put("INS_PROD_QTY_MONTH_MAX", 0L);

		PushJumpData.jumpDataMap.put("INS_PROD_QTY_YEAR_OLD", 0L);
		PushJumpData.jumpDataMap.put("INS_PROD_QTY_YEAR_MAX", 0L);

		PushJumpData.jumpDataMap.put("INS_SAL_QTY_MONTH_OLD", 0L);
		PushJumpData.jumpDataMap.put("INS_SAL_QTY_MONTH_MAX", 0L);

		PushJumpData.jumpDataMap.put("INS_SAL_QTY_YEAR_OLD", 0L);
		PushJumpData.jumpDataMap.put("INS_SAL_QTY_YEAR_MAX", 0L);

		PushJumpData.jumpDataMap.put("INS_SALSUM_QTY_OLD", 0L);
		PushJumpData.jumpDataMap.put("INS_SALSUM_QTY_MAX", 0L);

		PushJumpData.jumpDataMap.put("INS_SALSUM_QTY_MONTH_OLD", 0L);
		PushJumpData.jumpDataMap.put("INS_SALSUM_QTY_MONTH_MAX", 0L);

		PushJumpData.jumpDataMap.put("INS_SALSUM_QTY_YEAR_OLD", 0L);
		PushJumpData.jumpDataMap.put("INS_SALSUM_QTY_YEAR_MAX", 0L);

		PushJumpData.jumpDataMap.put("INS_DX_OLD", Double.valueOf(0));
		PushJumpData.jumpDataMap.put("INS_DX_MAX", Double.valueOf(0));

		PushJumpData.jumpDataMap.put("INS_JD_OLD", Double.valueOf(0));
		PushJumpData.jumpDataMap.put("INS_JD_MAX", Double.valueOf(0));

		PushJumpData.jumpDataMap.put("COM_BUY_QTY_MONTH_OLD", 0L);
		PushJumpData.jumpDataMap.put("COM_BUY_QTY_MONTH_MAX", 0L);

		PushJumpData.jumpDataMap.put("COM_BUY_QTY_YEAR_OLD", 0L);
		PushJumpData.jumpDataMap.put("COM_BUY_QTY_YEAR_MAX", 0L);

		PushJumpData.jumpDataMap.put("COM_SAL_QTY_MONTH_OLD", 0L);
		PushJumpData.jumpDataMap.put("COM_SAL_QTY_MONTH_MAX", 0L);

		PushJumpData.jumpDataMap.put("COM_SAL_QTY_YEAR_OLD", 0L);
		PushJumpData.jumpDataMap.put("COM_SAL_QTY_YEAR_MAX", 0L);

		PushJumpData.jumpDataMap.put("COM_SALSUM_QTY_OLD", 0L);
		PushJumpData.jumpDataMap.put("COM_SALSUM_QTY_MAX", 0L);

		PushJumpData.jumpDataMap.put("COM_SALSUM_QTY_MONTH_OLD", 0L);
		PushJumpData.jumpDataMap.put("COM_SALSUM_QTY_MONTH_MAX", 0L);

		PushJumpData.jumpDataMap.put("COM_SALSUM_QTY_YEAR_OLD", 0L);
		PushJumpData.jumpDataMap.put("COM_SALSUM_QTY_YEAR_MAX", 0L);

		PushJumpData.jumpDataMap.put("COM_DX_OLD", Double.valueOf(0));
		PushJumpData.jumpDataMap.put("COM_DX_MAX", Double.valueOf(0));

		PushJumpData.jumpDataMap.put("COM_JD_OLD", Double.valueOf(0));
		PushJumpData.jumpDataMap.put("COM_JD_MAX", Double.valueOf(0));
	}

	@Override
	protected long getSleepTime() {
		// 单位：ms
		return 30000;
	}

	public long splitVal(long oldVal, long newVal) {
		/*
		 * long randNum = new Random().nextInt(3); long incrVal = etlVal + randNum; if
		 * (incrVal < etlMaxVal) { return incrVal; } else { return etlMaxVal; }
		 */
//		return newVal > oldVal ? newVal : oldVal;
		return newVal;
	}

	public double splitVal1(double oldVal, double newVal) {
		return newVal;
	}

	@Override
	protected Event pullEvent() {
		PushJumpDataVO pjdvo = new PushJumpDataVO();

		Long INS_PROD_QTY_OLD = (Long) PushJumpData.jumpDataMap.get("INS_PROD_QTY_OLD");
		Long INS_PROD_QTY_MAX = (Long) PushJumpData.jumpDataMap.get("INS_PROD_QTY_MAX");

		Long INS_SAL_QTY_OLD = (Long) PushJumpData.jumpDataMap.get("INS_SAL_QTY_OLD");
		Long INS_SAL_QTY_MAX = (Long) PushJumpData.jumpDataMap.get("INS_SAL_QTY_MAX");

		Long INS_STK_QTY_OLD = (Long) PushJumpData.jumpDataMap.get("INS_STK_QTY_OLD");
		Long INS_STK_QTY_MAX = (Long) PushJumpData.jumpDataMap.get("INS_STK_QTY_MAX");

		Long COM_BUY_QTY_OLD = (Long) PushJumpData.jumpDataMap.get("COM_BUY_QTY_OLD");
		Long COM_BUY_QTY_MAX = (Long) PushJumpData.jumpDataMap.get("COM_BUY_QTY_MAX");

		Long COM_SAL_QTY_OLD = (Long) PushJumpData.jumpDataMap.get("COM_SAL_QTY_OLD");
		Long COM_SAL_QTY_MAX = (Long) PushJumpData.jumpDataMap.get("COM_SAL_QTY_MAX");

		Long COM_STK_QTY_OLD = (Long) PushJumpData.jumpDataMap.get("COM_STK_QTY_OLD");
		Long COM_STK_QTY_MAX = (Long) PushJumpData.jumpDataMap.get("COM_STK_QTY_MAX");

		Long TRANSIT_QTY_OLD = (Long) PushJumpData.jumpDataMap.get("TRANSIT_QTY_OLD");
		Long TRANSIT_QTY_MAX = (Long) PushJumpData.jumpDataMap.get("TRANSIT_QTY_MAX");

		Long INS_PROD_QTY_MONTH_OLD = (Long) PushJumpData.jumpDataMap.get("INS_PROD_QTY_MONTH_OLD");
		Long INS_PROD_QTY_MONTH_MAX = (Long) PushJumpData.jumpDataMap.get("INS_PROD_QTY_MONTH_MAX");

		Long INS_PROD_QTY_YEAR_OLD = (Long) PushJumpData.jumpDataMap.get("INS_PROD_QTY_YEAR_OLD");
		Long INS_PROD_QTY_YEAR_MAX = (Long) PushJumpData.jumpDataMap.get("INS_PROD_QTY_YEAR_MAX");

		Long INS_SAL_QTY_MONTH_OLD = (Long) PushJumpData.jumpDataMap.get("INS_SAL_QTY_MONTH_OLD");
		Long INS_SAL_QTY_MONTH_MAX = (Long) PushJumpData.jumpDataMap.get("INS_SAL_QTY_MONTH_MAX");

		Long INS_SAL_QTY_YEAR_OLD = (Long) PushJumpData.jumpDataMap.get("INS_SAL_QTY_YEAR_OLD");
		Long INS_SAL_QTY_YEAR_MAX = (Long) PushJumpData.jumpDataMap.get("INS_SAL_QTY_YEAR_MAX");

		Long INS_SALSUM_QTY_OLD = (Long) PushJumpData.jumpDataMap.get("INS_SALSUM_QTY_OLD");
		Long INS_SALSUM_QTY_MAX = (Long) PushJumpData.jumpDataMap.get("INS_SALSUM_QTY_MAX");

		Long INS_SALSUM_QTY_MONTH_OLD = (Long) PushJumpData.jumpDataMap.get("INS_SALSUM_QTY_MONTH_OLD");
		Long INS_SALSUM_QTY_MONTH_MAX = (Long) PushJumpData.jumpDataMap.get("INS_SALSUM_QTY_MONTH_MAX");

		Long INS_SALSUM_QTY_YEAR_OLD = (Long) PushJumpData.jumpDataMap.get("INS_SALSUM_QTY_YEAR_OLD");
		Long INS_SALSUM_QTY_YEAR_MAX = (Long) PushJumpData.jumpDataMap.get("INS_SALSUM_QTY_YEAR_MAX");

		Double INS_DX_OLD = (Double) PushJumpData.jumpDataMap.get("INS_DX_OLD");
		Double INS_DX_MAX = (Double) PushJumpData.jumpDataMap.get("INS_DX_MAX");

		Double INS_JD_OLD = (Double) PushJumpData.jumpDataMap.get("INS_JD_OLD");
		Double INS_JD_MAX = (Double) PushJumpData.jumpDataMap.get("INS_JD_MAX");

		Long COM_BUY_QTY_MONTH_OLD = (Long) PushJumpData.jumpDataMap.get("COM_BUY_QTY_MONTH_OLD");
		Long COM_BUY_QTY_MONTH_MAX = (Long) PushJumpData.jumpDataMap.get("COM_BUY_QTY_MONTH_MAX");

		Long COM_BUY_QTY_YEAR_OLD = (Long) PushJumpData.jumpDataMap.get("COM_BUY_QTY_YEAR_OLD");
		Long COM_BUY_QTY_YEAR_MAX = (Long) PushJumpData.jumpDataMap.get("COM_BUY_QTY_YEAR_MAX");

		Long COM_SAL_QTY_MONTH_OLD = (Long) PushJumpData.jumpDataMap.get("COM_SAL_QTY_MONTH_OLD");
		Long COM_SAL_QTY_MONTH_MAX = (Long) PushJumpData.jumpDataMap.get("COM_SAL_QTY_MONTH_MAX");

		Long COM_SAL_QTY_YEAR_OLD = (Long) PushJumpData.jumpDataMap.get("COM_SAL_QTY_YEAR_OLD");
		Long COM_SAL_QTY_YEAR_MAX = (Long) PushJumpData.jumpDataMap.get("COM_SAL_QTY_YEAR_MAX");

		Long COM_SALSUM_QTY_OLD = (Long) PushJumpData.jumpDataMap.get("COM_SALSUM_QTY_OLD");
		Long COM_SALSUM_QTY_MAX = (Long) PushJumpData.jumpDataMap.get("COM_SALSUM_QTY_MAX");

		Long COM_SALSUM_QTY_MONTH_OLD = (Long) PushJumpData.jumpDataMap.get("COM_SALSUM_QTY_MONTH_OLD");
		Long COM_SALSUM_QTY_MONTH_MAX = (Long) PushJumpData.jumpDataMap.get("COM_SALSUM_QTY_MONTH_MAX");

		Long COM_SALSUM_QTY_YEAR_OLD = (Long) PushJumpData.jumpDataMap.get("COM_SALSUM_QTY_YEAR_OLD");
		Long COM_SALSUM_QTY_YEAR_MAX = (Long) PushJumpData.jumpDataMap.get("COM_SALSUM_QTY_YEAR_MAX");

		Double COM_DX_OLD = (Double) PushJumpData.jumpDataMap.get("COM_DX_OLD");
		Double COM_DX_MAX = (Double) PushJumpData.jumpDataMap.get("COM_DX_MAX");

		Double COM_JD_OLD = (Double) PushJumpData.jumpDataMap.get("COM_JD_OLD");
		Double COM_JD_MAX = (Double) PushJumpData.jumpDataMap.get("COM_JD_MAX");

		INS_PROD_QTY_OLD = splitVal(INS_PROD_QTY_OLD, INS_PROD_QTY_MAX);
		INS_SAL_QTY_OLD = splitVal(INS_SAL_QTY_OLD, INS_SAL_QTY_MAX);
		INS_STK_QTY_OLD = splitVal(INS_STK_QTY_OLD, INS_STK_QTY_MAX);
		COM_BUY_QTY_OLD = splitVal(COM_BUY_QTY_OLD, COM_BUY_QTY_MAX);
		COM_SAL_QTY_OLD = splitVal(COM_SAL_QTY_OLD, COM_SAL_QTY_MAX);
		COM_STK_QTY_OLD = splitVal(COM_STK_QTY_OLD, COM_STK_QTY_MAX);
		TRANSIT_QTY_OLD = splitVal(TRANSIT_QTY_OLD, TRANSIT_QTY_MAX);
		INS_PROD_QTY_MONTH_OLD = splitVal(INS_PROD_QTY_MONTH_OLD, INS_PROD_QTY_MONTH_MAX);
		INS_PROD_QTY_YEAR_OLD = splitVal(INS_PROD_QTY_YEAR_OLD, INS_PROD_QTY_YEAR_MAX);
		INS_SAL_QTY_MONTH_OLD = splitVal(INS_SAL_QTY_MONTH_OLD, INS_SAL_QTY_MONTH_MAX);
		INS_SAL_QTY_YEAR_OLD = splitVal(INS_SAL_QTY_YEAR_OLD, INS_SAL_QTY_YEAR_MAX);
		INS_SALSUM_QTY_OLD = splitVal(INS_SALSUM_QTY_OLD, INS_SALSUM_QTY_MAX);
		INS_SALSUM_QTY_MONTH_OLD = splitVal(INS_SALSUM_QTY_MONTH_OLD, INS_SALSUM_QTY_MONTH_MAX);
		INS_SALSUM_QTY_YEAR_OLD = splitVal(INS_SALSUM_QTY_YEAR_OLD, INS_SALSUM_QTY_YEAR_MAX);
		INS_DX_OLD = splitVal1(INS_DX_OLD, INS_DX_MAX);
		INS_JD_OLD = splitVal1(INS_JD_OLD, INS_JD_MAX);
		COM_BUY_QTY_MONTH_OLD = splitVal(COM_BUY_QTY_MONTH_OLD, COM_BUY_QTY_MONTH_MAX);
		COM_BUY_QTY_YEAR_OLD = splitVal(COM_BUY_QTY_YEAR_OLD, COM_BUY_QTY_YEAR_MAX);
		COM_SAL_QTY_MONTH_OLD = splitVal(COM_SAL_QTY_MONTH_OLD, COM_SAL_QTY_MONTH_MAX);
		COM_SAL_QTY_YEAR_OLD = splitVal(COM_SAL_QTY_YEAR_OLD, COM_SAL_QTY_YEAR_MAX);
		COM_SALSUM_QTY_OLD = splitVal(COM_SALSUM_QTY_OLD, COM_SALSUM_QTY_MAX);
		COM_SALSUM_QTY_MONTH_OLD = splitVal(COM_SALSUM_QTY_MONTH_OLD, COM_SALSUM_QTY_MONTH_MAX);
		COM_SALSUM_QTY_YEAR_OLD = splitVal(COM_SALSUM_QTY_YEAR_OLD, COM_SALSUM_QTY_YEAR_MAX);
		COM_DX_OLD = splitVal1(COM_DX_OLD, COM_DX_MAX);
		COM_JD_OLD = splitVal1(COM_JD_OLD, COM_JD_MAX);

		PushJumpData.jumpDataMap.put("INS_PROD_QTY_OLD", INS_PROD_QTY_OLD);
		PushJumpData.jumpDataMap.put("INS_SAL_QTY_OLD", INS_SAL_QTY_OLD);
		PushJumpData.jumpDataMap.put("INS_STK_QTY_OLD", INS_STK_QTY_OLD);
		PushJumpData.jumpDataMap.put("COM_BUY_QTY_OLD", COM_BUY_QTY_OLD);
		PushJumpData.jumpDataMap.put("COM_SAL_QTY_OLD", COM_SAL_QTY_OLD);
		PushJumpData.jumpDataMap.put("COM_STK_QTY_OLD", COM_STK_QTY_OLD);
		PushJumpData.jumpDataMap.put("TRANSIT_QTY_OLD", TRANSIT_QTY_OLD);
		PushJumpData.jumpDataMap.put("INS_PROD_QTY_MONTH_OLD", INS_PROD_QTY_MONTH_OLD);
		PushJumpData.jumpDataMap.put("INS_PROD_QTY_YEAR_OLD", INS_PROD_QTY_YEAR_OLD);
		PushJumpData.jumpDataMap.put("INS_SAL_QTY_MONTH_OLD", INS_SAL_QTY_MONTH_OLD);
		PushJumpData.jumpDataMap.put("INS_SAL_QTY_YEAR_OLD", INS_SAL_QTY_YEAR_OLD);
		PushJumpData.jumpDataMap.put("INS_SALSUM_QTY_OLD", INS_SALSUM_QTY_OLD);
		PushJumpData.jumpDataMap.put("INS_SALSUM_QTY_MONTH_OLD", INS_SALSUM_QTY_MONTH_OLD);
		PushJumpData.jumpDataMap.put("INS_SALSUM_QTY_YEAR_OLD", INS_SALSUM_QTY_YEAR_OLD);
		PushJumpData.jumpDataMap.put("INS_DX_OLD", INS_DX_OLD);
		PushJumpData.jumpDataMap.put("INS_JD_OLD", INS_JD_OLD);
		PushJumpData.jumpDataMap.put("COM_BUY_QTY_MONTH_OLD", COM_BUY_QTY_MONTH_OLD);
		PushJumpData.jumpDataMap.put("COM_BUY_QTY_YEAR_OLD", COM_BUY_QTY_YEAR_OLD);
		PushJumpData.jumpDataMap.put("COM_SAL_QTY_MONTH_OLD", COM_SAL_QTY_MONTH_OLD);
		PushJumpData.jumpDataMap.put("COM_SAL_QTY_YEAR_OLD", COM_SAL_QTY_YEAR_OLD);
		PushJumpData.jumpDataMap.put("COM_SALSUM_QTY_OLD", COM_SALSUM_QTY_OLD);
		PushJumpData.jumpDataMap.put("COM_SALSUM_QTY_MONTH_OLD", COM_SALSUM_QTY_MONTH_OLD);
		PushJumpData.jumpDataMap.put("COM_SALSUM_QTY_YEAR_OLD", COM_SALSUM_QTY_YEAR_OLD);
		PushJumpData.jumpDataMap.put("COM_DX_OLD", COM_DX_OLD);
		PushJumpData.jumpDataMap.put("COM_JD_OLD", COM_JD_OLD);

		pjdvo.setCOM_BUY_QTY(COM_BUY_QTY_OLD + "");
		pjdvo.setCOM_SAL_QTY(COM_SAL_QTY_OLD + "");
		pjdvo.setCOM_STK_QTY(COM_STK_QTY_OLD + "");
		pjdvo.setINS_PROD_QTY(INS_PROD_QTY_OLD + "");
		pjdvo.setINS_SAL_QTY(INS_SAL_QTY_OLD + "");
		pjdvo.setINS_STK_QTY(INS_STK_QTY_OLD + "");
		pjdvo.setTRANSIT_QTY(TRANSIT_QTY_OLD + "");
		pjdvo.setRenderSpeed(Constant.rendertime);
		pjdvo.setCOM_BUY_QTY_MONTH(COM_BUY_QTY_MONTH_OLD + "");
		pjdvo.setCOM_BUY_QTY_YEAR(COM_BUY_QTY_YEAR_OLD + "");
		pjdvo.setCOM_SAL_QTY_MONTH(COM_SAL_QTY_MONTH_OLD + "");
		pjdvo.setCOM_SAL_QTY_YEAR(COM_SAL_QTY_YEAR_OLD + "");
		pjdvo.setCOM_SALSUM_QTY(COM_SALSUM_QTY_OLD + "");
		pjdvo.setCOM_SALSUM_QTY_MONTH(COM_SALSUM_QTY_MONTH_OLD + "");
		pjdvo.setCOM_SALSUM_QTY_YEAR(COM_SALSUM_QTY_YEAR_OLD + "");
		pjdvo.setCOM_DX(COM_DX_OLD + "");
		pjdvo.setCOM_JD(COM_JD_OLD + "");
		pjdvo.setINS_PROD_QTY_MONTH(INS_PROD_QTY_MONTH_OLD + "");
		pjdvo.setINS_PROD_QTY_YEAR(INS_PROD_QTY_YEAR_OLD + "");
		pjdvo.setINS_SAL_QTY_MONTH(INS_SAL_QTY_MONTH_OLD + "");
		pjdvo.setINS_SAL_QTY_YEAR(INS_SAL_QTY_YEAR_OLD + "");
		pjdvo.setINS_SALSUM_QTY(INS_SALSUM_QTY_OLD + "");
		pjdvo.setINS_SALSUM_QTY_MONTH(INS_SALSUM_QTY_MONTH_OLD + "");
		pjdvo.setINS_SALSUM_QTY_YEAR(INS_SALSUM_QTY_YEAR_OLD + "");
		pjdvo.setINS_DX(INS_DX_OLD + "");
		pjdvo.setINS_JD(INS_JD_OLD + "");

		Gson gson = new Gson();
		String s = gson.toJson(pjdvo);

		Event event = Event.createDataEvent("/linjiqin/hw");
		event.setField("hw", s);
		return event;
	}
}
