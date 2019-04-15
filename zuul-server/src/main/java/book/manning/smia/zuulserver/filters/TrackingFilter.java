package book.manning.smia.zuulserver.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Log
public class TrackingFilter extends ZuulFilter {
    @Autowired
    private FilterUtils filterUtils;

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    private boolean isCorrelationIdPresent() {
        return filterUtils.getCorrelationId() != null;
    }

    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Object run() throws ZuulException {
        if (isCorrelationIdPresent())
            log.info(FilterUtils.CORRELATION_ID + " found in TrackingFilter: " + filterUtils.getCorrelationId());
        else {
            filterUtils.setCorrelationId(generateCorrelationId());
            log.info(FilterUtils.CORRELATION_ID + " generated in TrackingFilter: " + filterUtils.getCorrelationId());
        }
        log.info("Processing incoming request for " + RequestContext.getCurrentContext().getRequest().getRequestURI());
        return null;
    }
}
