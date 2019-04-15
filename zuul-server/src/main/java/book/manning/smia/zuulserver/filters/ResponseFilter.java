package book.manning.smia.zuulserver.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log
public class ResponseFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterUtils.POST_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Autowired
    private FilterUtils filterUtils;

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        log.info("Adding the correlation id to the outbound headers: " + filterUtils.getCorrelationId());
        context.getResponse().addHeader(FilterUtils.CORRELATION_ID, filterUtils.getCorrelationId());
        log.info("Completing outgoing request for " + context.getRequest().getRequestURI());
        return null;
    }
}
