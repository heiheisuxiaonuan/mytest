package com.icss.cache.bussiness.initialization.tjfx;

import com.google.gson.Gson;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.cache.bussiness.initialization.tjfx.vo.TjfxAllVO;
import com.icss.tjfx.cigpop.cooperation.business.CooperEchartQuery;
import com.icss.tjfx.cigpop.cooperation.business.CooperTableQuery;
import com.icss.tjfx.cigpop.keypoint.bussiness.KeyPointEchartsQuery;
import com.icss.tjfx.cigpop.keypoint.bussiness.KeyPointTableQuery;
import com.icss.tjfx.cigpop.newcig.bussiness.NewCigEchartQuery;
import com.icss.tjfx.cigpop.newcig.bussiness.NewCigTableQuery;
import com.icss.tjfx.distribution.bussiness.Industry;
import com.icss.tjfx.distribution.bussiness.PopMap;
import com.icss.tjfx.distribution.bussiness.TaxProfit;
import com.icss.tjfx.innoproduct.productbz.bussiness.InnoProductBzCharts;
import com.icss.tjfx.innoproduct.productbz.bussiness.InnoProductBzTable;
import com.icss.tjfx.innoproduct.productdz.bussiness.InnoProductDzCharts;
import com.icss.tjfx.innoproduct.productdz.bussiness.InnoProductDzTable;
import com.icss.tjfx.innoproduct.productxz.bussiness.InnoProductXzCharts;
import com.icss.tjfx.innoproduct.productxz.bussiness.InnoProductXzTable;
import com.icss.tjfx.innoproduct.productzz.bussiness.InnoProductZzCharts;
import com.icss.tjfx.innoproduct.productzz.bussiness.InnoProductZzTable;
import com.icss.tjfx.leadtarget.macroscopic.business.MacroscopicChartsQuery;
import com.icss.tjfx.leadtarget.macroscopic.business.MacroscopicTableQuery;
import com.icss.tjfx.leadtarget.order.business.OrderChartsQuery;
import com.icss.tjfx.leadtarget.order.business.OrderTableQuery;
import com.icss.tjfx.leadtarget.transaction.business.TransactionChartsQuery;
import com.icss.tjfx.leadtarget.transaction.business.TransactionTableQuery;
import com.icss.tjfx.popcatalog.productinformation.bussiness.ProductInTableQuery;
import com.icss.tjfx.popcatalog.productlayout.bussiness.ProductLayOutCharsQuery;
import com.icss.tjfx.popcatalog.productlayout.bussiness.ProductLayOutTableQuery;
import com.icss.tjfx.total.bs.business.BsEchartsQuery;
import com.icss.tjfx.total.bs.business.BsTableQuery;
import com.icss.tjfx.total.in.business.InEchartsQuery;
import com.icss.tjfx.total.in.business.InTableQuery;
import com.icss.tjfx.total.structure.business.StructureEchartsQuery;
import com.icss.tjfx.total.structure.business.StructureTableQuery;
import com.icss.tjfx.trend.bussiness.PopScatter;
import com.icss.tjfx.trend.bussiness.TaxLine;
import com.icss.tjfx.twohighpop.highend.bussiness.HighEndEchartsQuery;
import com.icss.tjfx.twohighpop.highend.bussiness.HighEndTableQuery;
import com.icss.tjfx.twohighpop.highprice.bussiness.HighPriceEchartsQuery;
import com.icss.tjfx.twohighpop.highprice.bussiness.HighPriceTableQuery;

public class TjfxAllData {
		
	private static Log log  = LogFactory.getLog(TjfxAllData.class);
	
	public String dataInit() throws Exception{
		TjfxAllVO allVO=new TjfxAllVO();
		
//		log.info("---------------------??????????????????????????????----------------");
//		//????????????
//		InnoProductBzCharts bzCharts=new InnoProductBzCharts();
//		InnoProductBzTable bzTable=new InnoProductBzTable();
//		InnoProductDzCharts charts=new InnoProductDzCharts();
//		InnoProductDzTable dzTable=new InnoProductDzTable();
//		InnoProductXzCharts charts2=new InnoProductXzCharts();
//		InnoProductXzTable innoProductXzTable=new InnoProductXzTable();
//		InnoProductZzCharts zzcharts=new InnoProductZzCharts();
//		InnoProductZzTable zzTable=new InnoProductZzTable();
//		
//		//????????????
//		allVO.setInnoProductBzCharts(bzCharts.getInnoProductCharts());
//		allVO.setInnoProductBzTable(bzTable.getInNoProductTable());
//		allVO.setInnoProductDzCharts(charts.getInnoProductDzChars());
//		allVO.setInnoProductDzTable(dzTable.getInNoProductTable());
//		allVO.setInnoProductXzCharts(charts2.getInnoProductXzCharts());
//		allVO.setInnoProductXzTable(innoProductXzTable.getInNoProductTable());
//		allVO.setInnoProductZzCharts(zzcharts.getInnoProductDzChars());
//		allVO.setInnoProductZzTable(zzTable.getInNoProductTable());
//		log.info("---------------------??????????????????????????????----------------");
//		log.info("---------------------??????????????????????????????----------------");
//		//????????????
//		MacroscopicChartsQuery chartsQuery=new MacroscopicChartsQuery();
//		MacroscopicTableQuery macroscopicTableQuery=new MacroscopicTableQuery();
//		OrderChartsQuery chartsQuery2=new OrderChartsQuery();
//		OrderTableQuery orderTableQuery=new OrderTableQuery();
//		TransactionChartsQuery chartsQuery3=new TransactionChartsQuery();
//		TransactionTableQuery query=new TransactionTableQuery();
//		//????????????
//		allVO.setMacroscopicChars(chartsQuery.getMacroDate());
//		allVO.setMacroscopicTable(macroscopicTableQuery.getTransactionInfo("dw"));
//		allVO.setOrderCharts(chartsQuery2.getOrderDate());
//		allVO.setOrderTable(orderTableQuery.getOrderInfo("dw"));
//		allVO.setTransactionCharts(chartsQuery3.getTransactionData());
//		allVO.setTransactionTable(query.getTransactionInfo("dw"));
//		log.info("--------------------------??????????????????????????????--------------------");
//		log.info("--------------------------??????????????????????????????--------------------");
//		//????????????
//		BsEchartsQuery bsEchartsQuery=new BsEchartsQuery();
//		BsTableQuery bsTableQuery=new BsTableQuery();
//		InEchartsQuery inEchartsQuery=new InEchartsQuery();
//		InTableQuery tableQuery=new InTableQuery();
//		StructureEchartsQuery structureEchartsQuery=new StructureEchartsQuery();
//		StructureTableQuery structureTableQuery=new StructureTableQuery();
//		
//		CooperEchartQuery cooperEchartQuery=new CooperEchartQuery();
//		CooperTableQuery cooperTableQuery=new CooperTableQuery();
//		KeyPointEchartsQuery keyPointEchartsQuery=new KeyPointEchartsQuery();
//		KeyPointTableQuery keyPointTableQuery=new KeyPointTableQuery();
//		NewCigEchartQuery newCigEchartQuery=new NewCigEchartQuery();
//		NewCigTableQuery newCigTableQuery=new NewCigTableQuery();
//		//????????????
//		allVO.setBsEcharts(bsEchartsQuery.getBsEcharts());
//		allVO.setBsTable(bsTableQuery.getBsTableInfo("dw"));
//		allVO.setInEcharts(inEchartsQuery.getInEcharts());
//		allVO.setInTable(tableQuery.getInTableInfo("dw"));
//		allVO.setStructureEcharts(structureEchartsQuery.getStructureEcharts());
//		allVO.setPriTable(structureTableQuery.getTableInfo("dw"));
//		log.info("--------------------------??????????????????????????????---------------------");
//		log.info("--------------------------??????????????????????????????---------------------");
//		//????????????
//		allVO.setCooperVO(cooperEchartQuery.getCooperEchart());
//		allVO.setCooperListTable(cooperTableQuery.getTableInfo("dw"));
//		allVO.setKeyPointVO(keyPointEchartsQuery.getKeyPointEchartsVO());
//		allVO.setKeyPointTablelist(keyPointTableQuery.getTableInfo("dw"));
//		allVO.setNewCigVO(newCigEchartQuery.getNewCigVO());
//		allVO.setCigPopTableVOlist(newCigTableQuery.getTableInfo("dw"));
//		
//		log.info("----------------------------??????????????????????????????---------------------");
//		
//		log.info("----------------------------??????????????????????????????---------------------");
//		//????????????
//		ProductInTableQuery inTableQuery=new ProductInTableQuery();
//		ProductLayOutCharsQuery charsQuery=new ProductLayOutCharsQuery();
//		ProductLayOutTableQuery layOutTableQuery=new ProductLayOutTableQuery();
//		//????????????,??????????????????
//		allVO.setProductInList(inTableQuery.getProductInTableInSmoke("dw","11310001"));
//		allVO.setProductLayList(charsQuery.getProductLayCharsZY("dw","11310001"));
//		allVO.setProductLayTableList(layOutTableQuery.getProductLayTableZY("dw","11310001"));
//		log.info("----------------------------??????????????????????????????---------------------");
//		log.info("----------------------------??????????????????????????????---------------------");
//		//??????
//		HighEndEchartsQuery highEndEchartsQuery=new HighEndEchartsQuery();
//		HighEndTableQuery endTableQuery=new HighEndTableQuery();
//		HighPriceEchartsQuery highPriceEchartsQuery=new HighPriceEchartsQuery();
//		HighPriceTableQuery highPriceTableQuery=new HighPriceTableQuery();
//		//??????
//		allVO.setHighendEndEchars(highEndEchartsQuery.getHighEndEcharts());
//		allVO.setHighpriceEchars(highPriceEchartsQuery.getHighPriceEchars());
//		allVO.setHighpriceTable(highPriceTableQuery.getHighEndTable("dw"));
//		allVO.setTowHighEndTable(endTableQuery.getHighEndTable("dw"));
//		log.info("----------------------------??????????????????????????????---------------------");
		log.info("----------------------------???????????????????????????---------------------");
		
		//?????????
		PopScatter popScatter=new PopScatter();
		TaxLine line=new TaxLine();
		
		//?????????
		allVO.setPopSca(popScatter.getPopScaData());
		allVO.setTaxLine(line.getTaxLine());
		
		log.info("----------------------------???????????????????????????---------------------");
//		log.info("----------------------------???????????????????????????---------------------");
//		Industry industry=new Industry();
//		PopMap map=new PopMap();
//		TaxProfit profit=new TaxProfit();
//		
//		allVO.setIndustry(industry.getIndustyData("11310001"));
//		allVO.setPopMap(map.getPopMapData("1145"));
//		allVO.setTaxProfit(profit.getTaxProfit());
//		log.info("----------------------------???????????????????????????---------------------");
		Gson gson = new Gson();
		String s = gson.toJson(allVO);
		return s;
			
			
	}
}
