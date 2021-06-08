package com.icss.cache.bussiness.initialization.tjfx;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Element;

import com.icss.base.business.BaseBusiness;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;

/**
 * 判断并处理供应链的缓存
 * 
 * @author lkt
 * @since mar 16, 2016
 * @version 1.0
 * 
 */
public class TjfxAllCache extends BaseBusiness {

	private static Log log = LogFactory.getLog(TjfxAllCache.class);

	@Override
	public HttpServletRequest handle(HttpServletRequest request) throws Exception {
		// 从指定缓存中取出指定元素
//		Element element=null;
//		try {
//			element = CacheUtil.getElement("tjfxCache", "tjfx");
//		} catch (Exception e) {
//		}
		String tjfx = "";
//		//判断是否存在该元素
//		log.info("-----查看是否有缓存数据-----");
//		if(element == null){
		// 缓存不存在
		// 初始化驾驶舱的数据并存入缓存
		// log.info("缓存内无数据");
		TjfxAllData cd = new TjfxAllData();
		tjfx = cd.dataInit();
		log.info("查询成功");
		// log.info("缓存:"+tjfx);
		log.debug(tjfx);
		// CacheUtil.putElement("tjfxCache", "tjfx", tjfx);
//		}else{
//			//缓存存在
//			//将缓存中信息取出
//			log.info("缓存内有数据");
//			tjfx = (String)element.getObjectValue();
//			request.setAttribute("result", "重置缓存成功");
//		}

		if (request != null) {
			// 将缓存存入request对象中返回
			request.setAttribute("tjfx", tjfx);
		}

		return request;
	}

}
