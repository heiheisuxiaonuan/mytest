
package com.icss.base.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.base.business.BaseBusiness;
import com.icss.base.logging.Log;
import com.icss.base.logging.LogFactory;

public abstract class BaseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static Log log = LogFactory.getLog(BaseServlet.class);

    public static final String ERROR_PAGE_URL = "/jsp/common/CustomErr.jsp";

    public BaseServlet() {
        super();
    }

    /**
     * doGet
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
    	doPost(request,response);
    }

    /**
     * doPost
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            process(request, response);
        } catch (Exception e) {
            log.error("this unknown exception is posted to common err page!",e);
            handleError(request, response, e);
        }
    }

    /**
     *
     * @param request HttpServletReqeust
     * @param response HttpServletResponse
     * @param String url
     */
    public void forward(HttpServletRequest request,
            HttpServletResponse response, String url) {
        String newUrl = url;
        try {
            getServletContext().getRequestDispatcher(newUrl).forward(request,response);
        } catch (Exception e) {
            log.error("catch exception in forward().", e);
        }
    }

    /**
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param e java.lang.Exception
     */
    public void handleError(HttpServletRequest request,
            HttpServletResponse response, Exception e) {
        forward(request, response, ERROR_PAGE_URL);
        return;
    }

    /**
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exception
     */
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (log.isDebugEnabled())
            log.debug(getClass().getName() + " process begin.");

        performTask(request, response);

        if (log.isDebugEnabled())
            log.debug(getClass().getName() + " process end.");
    }

    /**
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public abstract void performTask(HttpServletRequest request,
            HttpServletResponse response);

    /**
     * @param request
     * @param response
     * @param packagePath 业务类的包路径 eg com.icss.sccm.predeal.business
     * @param jspPath     显示页的路径   eg /sccm/jsp/predeal
     * @throws Exception
     * @author wangyu
     * @since Jun 12, 2009
     */
    public void deal(HttpServletRequest request, HttpServletResponse response,
    		String packagePath, String jspPath) throws Exception{
		String className = request.getParameter("flag");
		String ajxa = request.getParameter("ajxa");
		//说明不是ajax
		if(ajxa==null){
			ajxa="N";
		}
		Class strClass = Class.forName(packagePath+"."+ className);
		Constructor constructor = strClass.getConstructor();
		BaseBusiness business = (BaseBusiness) constructor.newInstance();
		try {
			request = business.handle(request);
		} catch (Exception e) {
			log.error("调用发生异常 " + className, e);
			if("N".equals(ajxa)){
				throw e;
			}
		}
		
		if("N".equals(ajxa)){
			String jspName = (String) request.getAttribute("jspName");
			String url = jspPath+"/" + jspName;
			log.debug("url：" + url);
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			forward(request, response, url);
		}else{
			StringBuffer returnContent =  new StringBuffer();
			returnContent.append(request.getAttribute("result"));
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = null;
			try {
				out=response.getWriter();
				log.debug(returnContent.toString());
				out.write(returnContent.toString());
				out.close();
			} catch (IOException e) {
				log.error("IO流异常",e);
			}finally{
				if(out!=null)out.close();
			}
		}
    }

}
