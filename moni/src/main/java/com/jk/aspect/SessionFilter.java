package com.jk.aspect;

import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * 过滤器
 */
@WebFilter(filterName="firstFilter", urlPatterns = {"/*"})  
public class SessionFilter implements Filter{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SessionFilter.class);


    /**
     * 封装，不需要过滤的list列表
     */
    protected static List<Pattern> patterns = new ArrayList<Pattern>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        logger.info("aaaaaaaaaa");
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (url.startsWith("/") && url.length() > 1) {
            url = url.substring(1);
        }

        if (isInclude(url)){
            chain.doFilter(httpRequest, httpResponse);
            return;
        } else {
            HttpSession session = httpRequest.getSession();
            if (session.getAttribute("auth") != null){
            	Map<String, String[]> m = new HashMap<String, String[]>(httpRequest.getParameterMap());
            	m.put("auth", new String[] { session.getAttribute("auth").toString() });
            	httpRequest = new ParameterRequestWrapper(httpRequest, m);
                // session存在
                chain.doFilter(httpRequest, httpResponse);
                return;
            } else {
            	// session不存在 准备跳转失败
                /* RequestDispatcher dispatcher = request.getRequestDispatcher(path);
                    dispatcher.forward(request, response);*/
                chain.doFilter(httpRequest, httpResponse);
                return;
            }
        }


    }
    /**
     * 是否需要过滤
     * @param url
     * @return
     */
    private boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

	@Override
	public void destroy() {
		
	}

}
