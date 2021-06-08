
package com.icss.base.business;

import javax.servlet.http.HttpServletRequest;

import com.icss.base.util.StringUtil;

public abstract class BaseBusiness {

	public abstract HttpServletRequest handle(HttpServletRequest request)throws Exception;

    /**
     * @param request
     * @param parameterName
     * @param defaultValue
     * @return
     * @throws Exception
     * @author wangyu
     * @since Jun 12, 2009
     */
    protected String getParameter(HttpServletRequest request,
            String parameterName,String defaultValue) {
        String[] parameterValues = null;
        String paramValue = null;
        parameterValues = request.getParameterValues(parameterName);
        if (parameterValues != null)
            paramValue = parameterValues[0];

        if (paramValue != null) {
            paramValue = paramValue.trim();
            paramValue = StringUtil.encode(paramValue);
        }
        if (paramValue != null) {
            paramValue = StringUtil.processSingleQuotes(paramValue);
        }
        if (paramValue == null) {
            paramValue = defaultValue;
        }
        return paramValue;
    }

    /**
     * @param request
     * @param parameterName
     * @param defaultValue
     * @return
     * @throws Exception
     * @author wangyu
     * @since Jun 12, 2009
     */
    protected String[] getParameterValues(HttpServletRequest request,
            String parameterName,String[] defaultValue) {
        String[] parameterValues = null;
        parameterValues = request.getParameterValues(parameterName);
        if (parameterValues != null) {
            for (int i = 0; i < parameterValues.length; i++) {
                if (parameterValues[i] != null) {
                    parameterValues[i] = parameterValues[i].trim();
                    parameterValues[i] = StringUtil.encode(parameterValues[i]);
                    parameterValues[i] = StringUtil.processSingleQuotes(parameterValues[i]);
                }
            }
        }
        if (parameterValues == null) {
            parameterValues = defaultValue;
        }
        return parameterValues;
    }
}
