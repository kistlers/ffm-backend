package ch.ffm.data.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogRequestInterceptor implements HandlerInterceptor, Filter {

    private static final String X_FORWARDED_FOR_HEADER = "X-FORWARDED-FOR";

    private static final Logger logger = LoggerFactory.getLogger(LogRequestInterceptor.class);

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) {
        log(request, response);
    }

    private void log(HttpServletRequest request, HttpServletResponse response) {
        String ipAddress = request.getHeader(X_FORWARDED_FOR_HEADER);
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        String userName = "N/A";
        try {
            //            userName = SecurityHelper.getUserName();
        } catch (Exception ignored) {
        }
        logger.info(
                "Request: "
                        + request.getRequestURI()
                        + " | Method: "
                        + request.getMethod()
                        + " | HTTP Status: "
                        + response.getStatus()
                        + " | IP: "
                        + ipAddress
                        + " | userName: "
                        + userName);
    }

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            log(httpRequest, httpResponse);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {}
}
