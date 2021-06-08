package com.icss.welcome.bussiness.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.icss.base.db.DBBeanBase;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.base.util.DateUtil;
import com.icss.welcome.bussiness.vo.MapDataVO;
import com.icss.welcome.bussiness.vo.MapFPNameVO;
import com.icss.welcome.bussiness.vo.MapLineVO;
import com.icss.welcome.bussiness.vo.MapModelVO;
import com.icss.welcome.bussiness.vo.MapSDataVO;
import com.icss.welcome.bussiness.vo.NkViewDdmIndustryProduce;

public class InsMap {

	private static Log log = LogFactory.getLog(InsMap.class);

	/**
	 * 欢迎也工业地图数据
	 * 
	 * 查询:MA_T_D_STAT_FACT_POP_FLOW_HOME、MA_T_FACT_POP、MA_T_PROV_ORG
	 * 查询:日统计分中烟工业销售流向数据表
	 * 
	 * 查询结果 fact_pop_code 中烟公司编码 belong_prov_name 中烟所在省份名称 prov_code; 省份名称 prov_name
	 * 省局名称 ins_sal_qty 工业销量
	 * 
	 * return modelList
	 * 
	 * @author
	 * @since 2015-12-10
	 * @version 1.0
	 * 
	 */

	private static String getMapValueSQL(String date, String factPopCode) {
		StringBuffer sql = new StringBuffer();
		sql.append("select                                                                                 ");
		sql.append("      		 PROV_NAME as name ,                                                         ");
		sql.append("      		 DECIMAL(round(INS_SAL_QTY/5,0) ,16,0) as value                              ");
		sql.append("      		from                                                                         ");
		sql.append("      		table(                                                                       ");
		sql.append("      		  select                                                                     ");
		sql.append("      		    DECODE(PROV_CODE,'990000','110000',PROV_CODE) AS PROV_CODE               ");
		sql.append("      		    ,SUM(INS_SAL_QTY)   AS INS_SAL_QTY                                       ");
		sql.append("      		  from MA_T_D_STAT_FACT_POP_FLOW_HOME                                        ");
		sql.append("      		    where FACT_POP_CODE = '").append(factPopCode)
				.append("'                               ");
		sql.append("      		    and DECIMAL(INS_SAL_QTY)/5 >0.4                                          ");
		sql.append("      		    and DATE ='").append(date).append("'                                          ");
		sql.append("			  GROUP BY DECODE(PROV_CODE,'990000','110000',PROV_CODE)                         ");
		sql.append("      		) a,                                  ");
		sql.append("      		table(                                ");
		sql.append("      		  select                              ");
		sql.append("      		    PROV_CODE,                        ");
		sql.append("      		    PROV_NAME                         ");
		sql.append("      		  from MA_T_PROV_ORG                  ");
		sql.append("      		) b                                   ");
		sql.append("      		where a.PROV_CODE=b.PROV_CODE         ");
		sql.append("      		with ur                               ");
		log.debug("工业地图标注数据sql: " + sql.toString());
		return sql.toString();
	}

	private static String getMapNameSQL(String date, String factPopCode) {

		StringBuffer sql = new StringBuffer();
		sql.append("select                                                                                 ");
		sql.append("         		 DECODE(a.FACT_POP_CODE,'99000002','北京',BELONG_PROV_NAME) as name          ");
		sql.append("         		from                                                                       ");
		sql.append("         		table(                                                                     ");
		sql.append("         		  select                                                                   ");
		sql.append("					DECODE(PROV_CODE,'990000','110000',PROV_CODE) AS PROV_CODE                   ");
		sql.append("         		    ,MAX(FACT_POP_CODE)  AS FACT_POP_CODE                                  ");
		sql.append("         		  from MA_T_D_STAT_FACT_POP_FLOW_HOME                                      ");
		sql.append("         		    where FACT_POP_CODE = '").append(factPopCode)
				.append("'                             ");
		sql.append("         		    and DECIMAL(INS_SAL_QTY)/5 >0.4                                        ");
		sql.append("         		    and DATE ='").append(date).append("'                                        ");
		sql.append("				  GROUP BY DECODE(PROV_CODE,'990000','110000',PROV_CODE)                       ");
		sql.append("         		) a,                                       ");
		sql.append("         		table(                                     ");
		sql.append("         		  select                                   ");
		sql.append("         		    FACT_POP_CODE,                         ");
		sql.append("         		    BELONG_PROV_NAME                       ");
		sql.append("         		  from MA_T_FACT_POP                       ");
		sql.append("         		) b                                        ");
		sql.append("         		where a.FACT_POP_CODE=b.FACT_POP_CODE      ");
		sql.append("         		with ur                                    ");
		log.debug("工业地图标线数据部分sql: " + sql.toString());
		return sql.toString();
	}

	private static String getFactPopCodeSQL() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT       ");
		sql.append("factPopCode, factPopName, pronum1 ");
		sql.append("FROM ( ");
		sql.append("SELECT ");
		sql.append("		 a.FACT_POP_CODE as factPopCode,         ");
		sql.append("		 DECODE(a.FACT_POP_CODE,'99000002','北京(中烟实业)',b.BELONG_PROV_NAME) as factPopName,        ");
		sql.append("		 a.sumIns                                  ");
		sql.append("		from                                                 ");
		sql.append("		table(                                               ");
		sql.append("		  select                                             ");
		sql.append("		    FACT_POP_CODE,                                   ");
		sql.append("		    sum(INS_SAL_QTY) as sumIns                       ");
		sql.append("		  from MA_T_D_STAT_FACT_POP_FLOW_HOME                ");
		sql.append("		    where 1=1                                        ");
		sql.append("		    and INS_SAL_QTY >0                               ");
		sql.append("		  group by  FACT_POP_CODE                            ");
		sql.append("         having   sum(INS_SAL_QTY) > 0.4                 ");
		sql.append("		  order by sumIns desc                               ");
		sql.append("		) a,                                                 ");
		sql.append("		table(                                               ");
		sql.append("		  select                                             ");
		sql.append("		    FACT_POP_CODE,                                   ");
		sql.append("		    BELONG_PROV_NAME                                 ");
		sql.append("		  from MA_T_FACT_POP                                 ");
		sql.append("		) b                                                  ");
		sql.append("		where a.FACT_POP_CODE=b.FACT_POP_CODE )               ");
		sql.append("LEFT JOIN NK_VIEW_DDM_INDUSTRY_PRODUCE d ");
		sql.append("ON d.indu_code = factPopCode ");
		sql.append("		with ur                                              ");
		log.debug("工业地图存放满足条件的中烟公司名称（顺序）sql: " + sql.toString());
		return sql.toString();
	}

	private static String getPronumSQL() {
		StringBuffer sql = new StringBuffer();
		sql.append("select indu_code as induCode, pronum1 from nk_view_ddm_industry_produce");
		return sql.toString();
	}

	// 数据库连接dcma
	public List<MapModelVO> getInsMapData(String dbName) throws Exception {
		log.info("工业流向数据--------执行");
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String date = DateUtil.getNowDate();
		/*
		 * mapDataList （1）：标注数据 标线数据部分 mapSDataList（2）：标线数据部分 mapLineList
		 * （3）：标线数据=mapDataList+mapSDataList modelList （4）：全国的数据结果
		 * 
		 * （1）（2）拆开成MapDataVO和MapSDataVO放到MapLineVO中 MapLineVO添加到（3）中
		 * 把（1）和（3）放入mapModel(为一家中烟公司的数据)中 mapModel添加到（4） 最后返回（4）集合
		 */
		// 存放满足条件的中烟公司名称（顺序）（暂时编号升序）
		List<MapFPNameVO> factPopCodeList = new ArrayList();
		// 查询结果（1）（条件：1.昨天2.中烟公司3.销量大于0）
		List<MapDataVO> mapDataList = new ArrayList();
		// 查询结果（2）（条件：1.昨天2.中烟公司3.销量大于0）
		List<MapSDataVO> mapSDataList = new ArrayList();

		// （4）
		List<MapModelVO> modelList = new ArrayList();

		List<NkViewDdmIndustryProduce> industryProduceList = new ArrayList();

		String sql = "";
		try {
			sql = getFactPopCodeSQL();
			factPopCodeList = dbbean.executeQuery(sql, MapFPNameVO.class.getName());

			// 查询数据
			for (MapFPNameVO mfp : factPopCodeList) {
				String factPopCode = mfp.getFactPopCode();
				log.info("传递参数,当天时间：" + date + ",工业id：" + factPopCode);
				sql = getMapValueSQL(date, factPopCode);
				mapDataList = dbbean.executeQuery(sql, MapDataVO.class.getName());
				sql = getMapNameSQL(date, factPopCode);
				mapSDataList = dbbean.executeQuery(sql, MapSDataVO.class.getName());
				// （1）（2）拆开成MapDataVO和MapSDataVO放到MapLineVO中、
				// （3）
				List<MapLineVO> mapLineList = new ArrayList();
				for (int i = 0; i < mapDataList.size(); i++) {
					MapSDataVO mapName = new MapSDataVO();
					MapDataVO mapValue = new MapDataVO();
					mapValue = (MapDataVO) mapDataList.get(i);
					mapName = (MapSDataVO) mapSDataList.get(i);
					MapLineVO mapLine = new MapLineVO();
					mapLine.setMapdata(mapValue);
					mapLine.setMapsdata(mapName);
					// MapLineVO添加到（3）中
					mapLineList.add(mapLine);
				}
				// 把（1）和（3）放入mapModel(为一家中烟公司的数据)中 mapModel添加到（4）
				MapModelVO mapModel = new MapModelVO();
				mapModel.setMapLine(mapLineList);
				// mapModel.setMapPoint(mapDataList);
				mapModel.setTypeName(mfp.getFactPopName());
				mapModel.setPronum1(mfp.getPronum1());
				modelList.add(mapModel);
			}
		} catch (Exception e) {
			log.info("---------------------------------");
			log.info("工业地图执行sql异常" + e);
			log.error("工业地图执行sql执行异常", e);
			log.info("---------------------------------");
			throw new Exception("数据库查询异常：" + sql);
		} finally {
			dbbean.close();
		}
		log.debug("传入List<MapModelVO>对象：");

		return modelList;
	}

	public static void main(String[] args) throws Exception {
		InsMap insmap = new InsMap();
		List<MapModelVO> modelList = insmap.getInsMapData("dcma");
		// System.out.println(getFactPopCodeSQL());
		for (MapModelVO ml : modelList) {
			System.out.println(ml.getTypeName());
			for (MapDataVO mlvo : ml.getMapPoint()) {
				System.out.println(" name:" + mlvo.getName() + " value:" + mlvo.getValue());
			}
		}

//		String a = getFactPopCodeSQL();
//		System.out.println(a);
	}

}
