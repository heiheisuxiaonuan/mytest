package com.icss.cache.bussiness.initialization.tjfx.vo;

import java.util.List;

import com.icss.tjfx.cigpop.cooperation.vo.CooperTableVO;
import com.icss.tjfx.cigpop.keypoint.vo.KeyPointTableVO;
import com.icss.tjfx.cigpop.newcig.vo.CigPopNewTableVO;
import com.icss.tjfx.innoproduct.vo.BusinessSaleVo;
import com.icss.tjfx.innoproduct.vo.InNoProductTableVO;
import com.icss.tjfx.leadtarget.macroscopic.vo.MacroscopicCharsDataVO;
import com.icss.tjfx.leadtarget.macroscopic.vo.MacroscopicTableDataVO;
import com.icss.tjfx.leadtarget.order.vo.OrderChartsDataVO;
import com.icss.tjfx.leadtarget.order.vo.OrderTableDataVO;
import com.icss.tjfx.leadtarget.transaction.vo.TransactionChartsDataVO;
import com.icss.tjfx.leadtarget.transaction.vo.TransactionTableDataVO;
import com.icss.tjfx.popcatalog.productinformation.vo.ProductInTableVO;
import com.icss.tjfx.popcatalog.productlayout.vo.ProductLayCharsVO;
import com.icss.tjfx.popcatalog.productlayout.vo.ProductLayTableVO;
import com.icss.tjfx.total.bs.vo.BusinessVO;
import com.icss.tjfx.total.in.vo.IndustryVO;
import com.icss.tjfx.total.structure.vo.PriceClassVO;
import com.icss.tjfx.twohighpop.highprice.vo.TowHighTableVO;

public class TjfxAllVO {
	
	//品牌目录，产品信息，产品布局
	private List<ProductInTableVO> productInList;
	private List<ProductLayCharsVO> ProductLayList;
	private List<ProductLayTableVO> ProductLayTableList;
	//卷烟品牌
	private com.icss.tjfx.cigpop.cooperation.vo.ModelVO cooperVO;
	private List<CooperTableVO> cooperListTable;
	private com.icss.tjfx.cigpop.keypoint.vo.ModelVO keyPointVO;
	private List<KeyPointTableVO> KeyPointTablelist;
	private com.icss.tjfx.cigpop.newcig.vo.ModelVO newCigVO;
	private List<CigPopNewTableVO> cigPopTableVOlist;
	
	//创新品牌
	private BusinessSaleVo innoProductBzCharts;
	private List<InNoProductTableVO> innoProductBzTable;
	private BusinessSaleVo innoProductDzCharts;
	private List<InNoProductTableVO> innoProductDzTable;
	private BusinessSaleVo innoProductXzCharts;
	private List<InNoProductTableVO> innoProductXzTable;
	public BusinessSaleVo getInnoProductZzCharts() {
		return innoProductZzCharts;
	}
	public void setInnoProductZzCharts(BusinessSaleVo innoProductZzCharts) {
		this.innoProductZzCharts = innoProductZzCharts;
	}
	public List<InNoProductTableVO> getInnoProductZzTable() {
		return innoProductZzTable;
	}
	public void setInnoProductZzTable(List<InNoProductTableVO> innoProductZzTable) {
		this.innoProductZzTable = innoProductZzTable;
	}
	private BusinessSaleVo innoProductZzCharts;
	private List<InNoProductTableVO> innoProductZzTable;
	
	//先行指标
	private List<MacroscopicCharsDataVO> MacroscopicChars;
	private List<MacroscopicTableDataVO> MacroscopicTable;
	private List<OrderChartsDataVO> OrderCharts;
	private List<OrderTableDataVO> OrderTable;
	private List<TransactionChartsDataVO> TransactionCharts;
	private List<TransactionTableDataVO> TransactionTable;
	//总量情况
	
	private com.icss.tjfx.total.bs.vo.ModelVO BsEcharts;
	private com.icss.tjfx.total.in.vo.ModelVO InEcharts;
	private com.icss.tjfx.total.structure.vo.ModelVO StructureEcharts;
	private List<BusinessVO>  BsTable;
	private List<IndustryVO>  inTable;
	private List<PriceClassVO> priTable;
	
	//走势图
	private com.icss.tjfx.trend.vo.ModelVO popSca;
	private com.icss.tjfx.trend.vo.ModelVO taxLine;
	
	//两高
	private com.icss.tjfx.twohighpop.highend.vo.ModelVo highendEndEchars;
	private com.icss.tjfx.twohighpop.highend.vo.ModelVo highpriceEchars;
	private List<com.icss.tjfx.twohighpop.highend.vo.TowHighTableVO> towHighEndTable;
	private List<TowHighTableVO> highpriceTable;
	
	
	//分布图
	private List industry;
	private List popMap;
	private List TaxProfit;
	
	
	
	
	public List getIndustry() {
		return industry;
	}
	public void setIndustry(List industry) {
		this.industry = industry;
	}
	public List getPopMap() {
		return popMap;
	}
	public void setPopMap(List popMap) {
		this.popMap = popMap;
	}
	public List getTaxProfit() {
		return TaxProfit;
	}
	public void setTaxProfit(List taxProfit) {
		TaxProfit = taxProfit;
	}
	public com.icss.tjfx.cigpop.cooperation.vo.ModelVO getCooperVO() {
		return cooperVO;
	}
	public void setCooperVO(com.icss.tjfx.cigpop.cooperation.vo.ModelVO cooperVO) {
		this.cooperVO = cooperVO;
	}
	public com.icss.tjfx.cigpop.keypoint.vo.ModelVO getKeyPointVO() {
		return keyPointVO;
	}
	public void setKeyPointVO(com.icss.tjfx.cigpop.keypoint.vo.ModelVO keyPointVO) {
		this.keyPointVO = keyPointVO;
	}
	public com.icss.tjfx.cigpop.newcig.vo.ModelVO getNewCigVO() {
		return newCigVO;
	}
	public void setNewCigVO(com.icss.tjfx.cigpop.newcig.vo.ModelVO newCigVO) {
		this.newCigVO = newCigVO;
	}
	public List<CooperTableVO> getCooperListTable() {
		return cooperListTable;
	}
	public void setCooperListTable(List<CooperTableVO> cooperListTable) {
		this.cooperListTable = cooperListTable;
	}
	
	public List<KeyPointTableVO> getKeyPointTablelist() {
		return KeyPointTablelist;
	}
	public void setKeyPointTablelist(List<KeyPointTableVO> keyPointTablelist) {
		KeyPointTablelist = keyPointTablelist;
	}
	
	public List<CigPopNewTableVO> getCigPopTableVOlist() {
		return cigPopTableVOlist;
	}
	public void setCigPopTableVOlist(List<CigPopNewTableVO> cigPopTableVOlist) {
		this.cigPopTableVOlist = cigPopTableVOlist;
	}
	public BusinessSaleVo getInnoProductBzCharts() {
		return innoProductBzCharts;
	}
	public void setInnoProductBzCharts(BusinessSaleVo innoProductBzCharts) {
		this.innoProductBzCharts = innoProductBzCharts;
	}
	public List<InNoProductTableVO> getInnoProductBzTable() {
		return innoProductBzTable;
	}
	public void setInnoProductBzTable(List<InNoProductTableVO> innoProductBzTable) {
		this.innoProductBzTable = innoProductBzTable;
	}
	public BusinessSaleVo getInnoProductDzCharts() {
		return innoProductDzCharts;
	}
	public void setInnoProductDzCharts(BusinessSaleVo innoProductDzCharts) {
		this.innoProductDzCharts = innoProductDzCharts;
	}
	public List<InNoProductTableVO> getInnoProductDzTable() {
		return innoProductDzTable;
	}
	public void setInnoProductDzTable(List<InNoProductTableVO> innoProductDzTable) {
		this.innoProductDzTable = innoProductDzTable;
	}
	public BusinessSaleVo getInnoProductXzCharts() {
		return innoProductXzCharts;
	}
	public void setInnoProductXzCharts(BusinessSaleVo innoProductXzCharts) {
		this.innoProductXzCharts = innoProductXzCharts;
	}
	public List<InNoProductTableVO> getInnoProductXzTable() {
		return innoProductXzTable;
	}
	public void setInnoProductXzTable(List<InNoProductTableVO> innoProductXzTable) {
		this.innoProductXzTable = innoProductXzTable;
	}
	public List<MacroscopicCharsDataVO> getMacroscopicChars() {
		return MacroscopicChars;
	}
	public void setMacroscopicChars(List<MacroscopicCharsDataVO> macroscopicChars) {
		MacroscopicChars = macroscopicChars;
	}
	public List<MacroscopicTableDataVO> getMacroscopicTable() {
		return MacroscopicTable;
	}
	public void setMacroscopicTable(List<MacroscopicTableDataVO> macroscopicTable) {
		MacroscopicTable = macroscopicTable;
	}
	public List<OrderChartsDataVO> getOrderCharts() {
		return OrderCharts;
	}
	public void setOrderCharts(List<OrderChartsDataVO> orderCharts) {
		OrderCharts = orderCharts;
	}
	public List<OrderTableDataVO> getOrderTable() {
		return OrderTable;
	}
	public void setOrderTable(List<OrderTableDataVO> orderTable) {
		OrderTable = orderTable;
	}
	public List<TransactionChartsDataVO> getTransactionCharts() {
		return TransactionCharts;
	}
	public void setTransactionCharts(List<TransactionChartsDataVO> transactionCharts) {
		TransactionCharts = transactionCharts;
	}
	public List<TransactionTableDataVO> getTransactionTable() {
		return TransactionTable;
	}
	public void setTransactionTable(List<TransactionTableDataVO> transactionTable) {
		TransactionTable = transactionTable;
	}
	public com.icss.tjfx.total.bs.vo.ModelVO getBsEcharts() {
		return BsEcharts;
	}
	public void setBsEcharts(com.icss.tjfx.total.bs.vo.ModelVO bsEcharts) {
		BsEcharts = bsEcharts;
	}
	public com.icss.tjfx.total.in.vo.ModelVO getInEcharts() {
		return InEcharts;
	}
	public void setInEcharts(com.icss.tjfx.total.in.vo.ModelVO inEcharts) {
		InEcharts = inEcharts;
	}
	public com.icss.tjfx.total.structure.vo.ModelVO getStructureEcharts() {
		return StructureEcharts;
	}
	public void setStructureEcharts(
			com.icss.tjfx.total.structure.vo.ModelVO structureEcharts) {
		StructureEcharts = structureEcharts;
	}
	public List<BusinessVO> getBsTable() {
		return BsTable;
	}
	public void setBsTable(List<BusinessVO> bsTable) {
		BsTable = bsTable;
	}
	public List<IndustryVO> getInTable() {
		return inTable;
	}
	public void setInTable(List<IndustryVO> inTable) {
		this.inTable = inTable;
	}
	public List<PriceClassVO> getPriTable() {
		return priTable;
	}
	public void setPriTable(List<PriceClassVO> priTable) {
		this.priTable = priTable;
	}
	public com.icss.tjfx.trend.vo.ModelVO getPopSca() {
		return popSca;
	}
	public void setPopSca(com.icss.tjfx.trend.vo.ModelVO popSca) {
		this.popSca = popSca;
	}
	public com.icss.tjfx.trend.vo.ModelVO getTaxLine() {
		return taxLine;
	}
	public void setTaxLine(com.icss.tjfx.trend.vo.ModelVO taxLine) {
		this.taxLine = taxLine;
	}
	public com.icss.tjfx.twohighpop.highend.vo.ModelVo getHighendEndEchars() {
		return highendEndEchars;
	}
	public void setHighendEndEchars(
			com.icss.tjfx.twohighpop.highend.vo.ModelVo highendEndEchars) {
		this.highendEndEchars = highendEndEchars;
	}
	public com.icss.tjfx.twohighpop.highend.vo.ModelVo getHighpriceEchars() {
		return highpriceEchars;
	}
	public void setHighpriceEchars(
			com.icss.tjfx.twohighpop.highend.vo.ModelVo highpriceEchars) {
		this.highpriceEchars = highpriceEchars;
	}
	
	public List<com.icss.tjfx.twohighpop.highend.vo.TowHighTableVO> getTowHighEndTable() {
		return towHighEndTable;
	}
	public void setTowHighEndTable(
			List<com.icss.tjfx.twohighpop.highend.vo.TowHighTableVO> towHighEndTable) {
		this.towHighEndTable = towHighEndTable;
	}
	public List<TowHighTableVO> getHighpriceTable() {
		return highpriceTable;
	}
	public void setHighpriceTable(List<TowHighTableVO> highpriceTable) {
		this.highpriceTable = highpriceTable;
	}
	public List<ProductInTableVO> getProductInList() {
		return productInList;
	}
	public void setProductInList(List<ProductInTableVO> productInList) {
		this.productInList = productInList;
	}
	public List<ProductLayCharsVO> getProductLayList() {
		return ProductLayList;
	}
	public void setProductLayList(List<ProductLayCharsVO> productLayList) {
		ProductLayList = productLayList;
	}
	public List<ProductLayTableVO> getProductLayTableList() {
		return ProductLayTableList;
	}
	public void setProductLayTableList(List<ProductLayTableVO> productLayTableList) {
		ProductLayTableList = productLayTableList;
	}
	public TjfxAllVO(List<ProductInTableVO> productInList,
			List<ProductLayCharsVO> productLayList,
			List<ProductLayTableVO> productLayTableList) {
		super();
		this.productInList = productInList;
		ProductLayList = productLayList;
		ProductLayTableList = productLayTableList;
	}
	public TjfxAllVO() {
		super();
	}
}
