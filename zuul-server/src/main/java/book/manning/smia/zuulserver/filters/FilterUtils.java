package book.manning.smia.zuulserver.filters;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class FilterUtils {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN     = "tmx-auth-token";
    public static final String USER_ID        = "tmx-user-id";
    public static final String ORG_ID         = "tmx-org-id";
    public static final String PRE_FILTER_TYPE = "pre";
    public static final String POST_FILTER_TYPE = "post";
    public static final String ROUTE_FILTER_TYPE = "route";
    public static final String SERVICE_ID       = "service-id";

    public String getCorrelationId() {
        RequestContext context = RequestContext.getCurrentContext();
        if (context.getRequest().getHeader(CORRELATION_ID) != null)
            return context.getRequest().getHeader(CORRELATION_ID);
        else
            return context.getZuulRequestHeaders().get(CORRELATION_ID);
    }

    public final void setCorrelationId(String correlationId) {
        RequestContext.getCurrentContext().addZuulRequestHeader(CORRELATION_ID, correlationId);
    }


    public String getServiceId() {
        RequestContext context = RequestContext.getCurrentContext();
        if (context.getRequest().getHeader(SERVICE_ID) != null)
            return context.getRequest().getHeader(SERVICE_ID);
        else
            return context.getZuulRequestHeaders().get(SERVICE_ID);
    }

}
