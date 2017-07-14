package com.aisino.filter;

import com.aisino.tools.JsonWebTokenBuild;
import org.eclipse.jetty.server.HttpWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by gangchaopan on 2017/7/13.
 */
public class UserAuthFilter extends OncePerRequestFilter {
    Logger logger  = LoggerFactory.getLogger(UserAuthFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // no filter
        String[] noFilter = new String[]{"/login"};
        boolean doFilter  = true;
        String uri = httpServletRequest.getRequestURI();

        for (String s : noFilter){
            if (uri.indexOf(s) !=-1){
                doFilter = false;
                break;
            }
        }

        String token = httpServletRequest.getParameter("token");
        boolean isok = JsonWebTokenBuild.verifyToken(token);

        logger.info("token校验结果:{}",isok);
        if(!doFilter || !isok){
            httpServletResponse.setContentType("text/html;charset=utf-8");
             String  json = JsonWebTokenBuild.verify(token);
             logger.info("token校验结果:{}",json);
             PrintWriter writer =httpServletResponse.getWriter();
             writer.print(json);
             writer.flush();
             writer.close();

        }else{
            logger.info("token校验结果:{}","正常");
            // no filter
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }

    }
}
