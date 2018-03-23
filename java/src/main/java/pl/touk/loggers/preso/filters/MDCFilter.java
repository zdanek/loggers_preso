package pl.touk.loggers.preso.filters;

import org.slf4j.MDC;
import org.springframework.web.filter.GenericFilterBean;
import pl.touk.loggers.preso.config.Tracking;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter( filterName = "mdcFilter", urlPatterns = { "/*" } )
public class MDCFilter extends GenericFilterBean {

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpSession session = httpReq.getSession();

        String correlationId = session.getId();

        MDC.put(Tracking.CORRELATION_ID_MDC_KEY, correlationId);

        try {
            chain.doFilter(request, response);
        } finally {
            // When the control returns to the filter, clean it.
            MDC.remove(Tracking.CORRELATION_ID_MDC_KEY);
        }
    }

}
