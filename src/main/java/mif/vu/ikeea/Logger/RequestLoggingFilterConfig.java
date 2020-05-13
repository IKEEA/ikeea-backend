package mif.vu.ikeea.Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Configuration
public class RequestLoggingFilterConfig extends AbstractRequestLoggingFilter {

    @Bean
    public AbstractRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(false);
        return loggingFilter;
    }

    @Override
    protected void beforeRequest(HttpServletRequest httpServletRequest, String message) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //logger.debug( message + httpServletRequest.getMethod() + " " + auth.getAuthorities());
        //logger.debug( httpServletRequest.getMethod());

        logger.info( message + httpServletRequest.getMethod() + " " + auth.getAuthorities());
        logger.info( httpServletRequest.getMethod());
    }

    @Override
    protected void afterRequest(HttpServletRequest httpServletRequest, String message) {
    }
}
