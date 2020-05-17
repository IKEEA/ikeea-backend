package mif.vu.ikeea.Logger;
import mif.vu.ikeea.Entity.ApplicationUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class RequestLoggingFilterConfig extends AbstractRequestLoggingFilter {

    @Bean
    public AbstractRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(false);
        loggingFilter.setIncludeHeaders(false);
        loggingFilter.setMaxPayloadLength(10000);
        return loggingFilter;
    }

    @Override
    protected void beforeRequest(HttpServletRequest httpServletRequest, String message) {

        logger.info(formMessage(httpServletRequest, message));
    }

    @Override
    protected void afterRequest(HttpServletRequest httpServletRequest, String message) {
    }

    private String getCurrentDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private String formMessage(HttpServletRequest httpServletRequest, String message){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ApplicationUser applicationUser = null;

        if (principal instanceof ApplicationUser) {
            applicationUser = ((ApplicationUser) principal);
            message += " " + httpServletRequest.getMethod() + " " + getCurrentDateTime() + " " + applicationUser.getFirstName() + " " + applicationUser.getLastName() +  " " + auth.getAuthorities();
        } else {
            message += " " + httpServletRequest.getMethod() + " " + getCurrentDateTime() + " " + auth.getAuthorities();
        }

        return message;
    }
}
