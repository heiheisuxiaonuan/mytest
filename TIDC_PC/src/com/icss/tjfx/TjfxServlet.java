package com.icss.tjfx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;
import com.icss.base.servlet.BaseServlet;

public class TjfxServlet extends BaseServlet{
	private static Log log  = LogFactory.getLog(TjfxServlet.class);

	public void performTask(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			super.deal(request,response,"com.icss.tjfx","/jsp/tjfx");
		} catch (Exception e) {
			log.error("发生异常",e);
			handleError(request,response,e);
		}
	}
}
