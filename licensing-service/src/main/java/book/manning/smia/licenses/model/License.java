package book.manning.smia.licenses.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class License {
    private String id;
    private String organizationId;
    private String productName;
    private String licenseType;
}
