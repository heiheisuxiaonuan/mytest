package com.icss.welcome.bussiness.map;

import java.util.ArrayList;
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

public class ComMap {
	
	private static Log log = LogFactory.getLog(ComMap.class);
	/**
	 * 欢迎也商业地图数据
	 * 
	 * 查询:MA_T_D_STAT_PROV_FLOW_HOME、MA_T_FACT_POP、MA_T_PROV_ORG
	 * 查询:日统计分省商业购进流向数据表
	 * 
	 * 查询结果
	 * fact_pop_code			中烟公司编码
	 * belong_prov_name			中烟所在省份名称
	 * prov_code;				省份名称
	 * prov_name				省局名称
	 * com_buy_qty				商业购进量
	 * 
	 * return modelList 
	 * @author 
	 * @since 2015-12-10
	 * @version 1.0
	 * 
	 * */
	

	private static String getMapValueSQL(String date,String provCode){
		StringBuffer sql = new StringBuffer();
		sql.append("                                                                ");
		sql.append("select                                                          ");
		sql.append("		 PROV_NAME as name ,                                        ");
		sql.append("		 DECIMAL(round(COM_BUY_QTY/5,0) ,16,0) as value             ");
		sql.append("		from                                                        ");
		sql.append("		table(                                                      ");
		sql.append("		 select                                                     ");
		sql.append("		  PROV_CODE,                                                ");
		sql.append("		  COM_BUY_QTY                                               ");
		sql.append("		 from MA_T_D_STAT_PROV_FLOW_HOME                            ");
		sql.append("		  where PROV_CODE = '").append(provCode).append("'                   ");
		sql.append("		   and DECIMAL(COM_BUY_QTY)/5 >0.4                          ");
		sql.append("		   and DATE ='").append(date).append("'                        ");
		sql.append("		) a, table(                                                 ");
		sql.append("		 select                                                     ");
		sql.append("		  PROV_CODE,                                                ");
		sql.append("		  PROV_NAME                                                 ");
		sql.append("		 from MA_T_PROV_ORG                                         ");
		sql.append("		) b                                                         ");
		sql.append("		 where a.PROV_CODE=b.PROV_CODE                              ");
		sql.append("		 order by value desc                                        ");
		sql.append("		with ur                                                     ");
		log.info("商业地图标注数据  sql: "+sql.toString());
		return sql.toString();
	} 
	
	private static String getMapNameSQL(String date,String provCode){
		StringBuffer sql = new StringBuffer();
		sql.append("select                                                                                 ");
		sql.append("		DECODE(a.FACT_POP_CODE,'99000002','北京',BELONG_PROV_NAME) as name ,                 ");        
		sql.append("		DECIMAL(round(COM_BUY_QTY/5,0) ,16,0) as value          ");
		sql.append("    		from                       ");
		sql.append("    		table(                     ");
		sql.append("    		 select                    ");
		sql.append("    		  FACT_POP_CODE,           ");
		sql.append("    		  COM_BUY_QTY              ");
		sql.append("    		 from MA_T_D_STAT_PROV_FLOW_HOME                    ");
		sql.append("    		   where PROV_CODE = '").append(provCode).append("'             ");
		sql.append("    		    and DECIMAL(COM_BUY_QTY)/5 >0.4                 ");
		sql.append("    		    and DATE ='").append(date).append("'                 ");
		sql.append("    		) a, table(                                         ");
		sql.append("    		 select                                             ");
		sql.append("    		  FACT_POP_CODE,                                    ");
		sql.append("    		  BELONG_PROV_NAME                                  ");
		sql.append("    		 from MA_T_FACT_POP                                 ");
		sql.append("    		) b                                                 ");
		sql.append("    		 where a.FACT_POP_CODE=b.FACT_POP_CODE              ");
		sql.append("    		 order by value desc                                ");
		sql.append("    		with ur                                             ");
		log.info("商业地图标线数据部分sql: "+sql.toString());
		return sql.toString();
	} 	
	
	
	private static String getFactPopCodeSQL(){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("provCode, provName, out_num1_bs as outNumBs ");
		sql.append("FROM ( ");
		sql.append("select                                       ");
		sql.append(" distinct a.PROV_CODE as provCode,           ");
		sql.append(" b.PROV_NAME as provName,                    ");
		sql.append(" a.sumCom                                    ");
		sql.append("from table(                                  ");
		sql.append(" select PROV_CODE,                           ");
		sql.append("  sum(COM_BUY_QTY) as sumCom                 ");
		sql.append(" from MA_T_D_STAT_PROV_FLOW_HOME             ");
		sql.append("  where 1=1                                  ");
		sql.append("   and DECIMAL(COM_BUY_QTY)/5 >0.4           ");
		sql.append("  group by PROV_CODE                         ");
		sql.append(") a, table(                                  ");
		sql.append("  select PROV_CODE,                          ");
		sql.append("   PROV_NAME                                 ");
		sql.append("  from MA_T_PROV_ORG                         ");
		sql.append(") b                                          ");
		sql.append("where a.PROV_CODE=b.PROV_CODE                ");
		sql.append("  order by sumCom desc)                       ");
		sql.append("LEFT JOIN nk_view_ddm_business_produce d ");
		sql.append("ON d.come_code=provCode ");
		sql.append("with ur                                      ");
		log.info("商业地图存放满足条件的中烟公司名称（顺序）sql: "+sql.toString());
		return sql.toString();
	}
	
	
	//数据库连接dcma
	public List<MapModelVO> getComMapData(String dbName) throws Exception{
		log.info("商业流向数据--------执行");
		DBBeanBase dbbean = new DBBeanBase(dbName);
		String date = DateUtil.getNowDate();
		/*
		 * mapDataList （1）：标注数据         标线数据部分
		 * mapSDataList（2）：标线数据部分
		 * mapLineList （3）：标线数据=mapDataList+mapSDataList
		 * modelList   （4）：全国的数据结果
		 * 
		 * （1）（2）拆开成MapDataVO和MapSDataVO放到MapLineVO中
		 * MapLineVO添加到（3）中
		 * 把（1）和（3）放入mapModel(为一家中烟公司的数据)中
		 * mapModel添加到（4）
		 * 最后返回（4）集合
		 */
		//存放满足条件的中烟公司名称（顺序）（暂时编号升序）
		List<MapFPNameVO> provCodeList = new ArrayList();
		//查询结果（1）（条件：1.昨天2.中烟公司3.销量大于0）
		List<MapDataVO> mapDataList = new ArrayList();
		//查询结果（2）（条件：1.昨天2.中烟公司3.销量大于0）
		List<MapSDataVO> mapSDataList = new ArrayList();
		//用来存放标记的数据
		List<MapDataVO> mapPointDataList = new ArrayList();
		//（4）
		List<MapModelVO> modelList = new ArrayList();
		
		String sql = "";
		try {
			sql = getFactPopCodeSQL();
			provCodeList = dbbean.executeQuery(sql, MapFPNameVO.class.getName()); 
			//查询数据
			for(MapFPNameVO mfp:provCodeList){
				String provCode = mfp.getProvCode();
				log.info("传递参数,当天时间："+date+",商业id："+provCode);
				sql = getMapValueSQL(date,provCode);
				mapDataList = dbbean.executeQuery(sql, MapDataVO.class.getName()); 
				sql = getMapNameSQL(date,provCode);
				mapSDataList = dbbean.executeQuery(sql, MapSDataVO.class.getName()); 
				sql = getMapNameSQL(date,provCode);
				//mapPointDataList = dbbean.executeQuery(sql, MapDataVO.class.getName()); 
				//（1）（2）拆开成MapDataVO和MapSDataVO放到MapLineVO中、
				//（3）
				List<MapLineVO> mapLineList = new ArrayList();
				for(int i = 0;i<mapDataList.size();i++){
					MapSDataVO mapName = new MapSDataVO();
					MapDataVO mapValue = new MapDataVO();
					mapValue = (MapDataVO) mapDataList.get(i);     
					mapName = (MapSDataVO) mapSDataList.get(i);   
					MapLineVO mapLine = new MapLineVO();
					mapLine.setMapdata(mapValue);
					mapLine.setMapsdata(mapName);
					//MapLineVO添加到（3）中
					mapLineList.add(mapLine);
				}
				//把（1）和（3）放入mapModel(为一家中烟公司的数据)中 mapModel添加到（4）
				MapModelVO mapModel = new MapModelVO();
				mapModel.setMapLine(mapLineList);
				//mapModel.setMapPoint(mapPointDataList);
				mapModel.setTypeName(mfp.getProvName());
				mapModel.setOutNumBs(mfp.getOutNumBs());
				modelList.add(mapModel);
			}			
		}catch (Exception e) {
			log.info("---------------------------------");
			log.info("商业地图数据执行sql异常"+e);
			log.error("商业地图数据执行sql执行异常",e);
			log.info("---------------------------------");
		    throw new Exception("数据库查询异常："+sql);
		}finally{
			dbbean.close();
		}
		log.debug("传入List<MapModelVO>对象：");
		
		return modelList;
	}
	public static void main(String[] args) throws Exception {
		ComMap insmap = new ComMap();
		List<MapModelVO> modelList = insmap.getComMapData("dcma");
		for(MapModelVO ml : modelList){
			System.out.println(ml.getTypeName());
			for(MapDataVO mlvo: ml.getMapPoint()){
				System.out.println(" name:"+mlvo.getName()+" value:"+mlvo.getValue());
			}
		}
//		System.out.println(getFactPopCodeSQL());
	}
	
}
