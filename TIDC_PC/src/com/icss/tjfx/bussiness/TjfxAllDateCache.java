package com.icss.tjfx.bussiness;

import javax.servlet.http.HttpServletRequest;

import com.icss.base.business.BaseBusiness;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.cache.bussiness.initialization.tjfx.TjfxAllCache;

public class TjfxAllDateCache extends BaseBusiness{
	private static Log log  = LogFactory.getLog(TjfxAllDateCache.class);
	
	public HttpServletRequest handle(HttpServletRequest request) throws Exception {
		log.info("------------统计分析开始加载缓存-----------------");
		//创建统计分析缓存处理类
		TjfxAllCache cc = new TjfxAllCache();
		//获取统计分析需要的数据
		request=cc.handle(request);
		log.info("------------统计分析开始加载完成-----------------");
		String result = (String)request.getAttribute("tjfx");
		//将数据存入request对象中返回前台
		request.setAttribute("result", result);
		
		return request;
	}
}
