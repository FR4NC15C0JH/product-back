package br.com.produtos.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/**
 * Habilitando recursos HTTP spring
 * @author fr4nc15c0j0rg3
 *
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCORSFilter implements Filter{
	
	public final Log logger = LogFactory.getLog(SimpleCORSFilter.class);
	
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("By Products | SimpleCORSFilter loaded");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) resp;	
		HttpServletRequest request = (HttpServletRequest) req;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Expose-Headers", "Location");
        //usado com autenticação com token
        /*response.setHeader("Access-Control-Allow-Headers", 
				"x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");*/
		
		if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			chain.doFilter(req, resp);
		}
	}
}
