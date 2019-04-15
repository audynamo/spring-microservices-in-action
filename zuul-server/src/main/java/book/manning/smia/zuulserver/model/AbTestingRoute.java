package book.manning.smia.zuulserver.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AbTestingRoute {
    private String serviceName;
    private String active;
    private String endPoint;
    private Integer weight;
}
