package book.manning.smia.licenses.clients;

import book.manning.smia.licenses.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OrganizationDiscoveryClient {
    @Autowired
    private DiscoveryClient discoveryClient;

    public Organization getOrganization(String organizationId) {
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("organization-service");
        if (serviceInstanceList.isEmpty()) return null;
        RestTemplate restTemplate = new RestTemplate();
        String serviceUri = String.format("%s/v1/organizations/%s", serviceInstanceList.get(0).getUri().toString(), organizationId);
        ResponseEntity<Organization> restExchange = restTemplate.exchange(serviceUri, HttpMethod.GET, null, Organization.class, organizationId);
        return restExchange.getBody();
    }
}
