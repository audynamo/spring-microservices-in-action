package book.manning.smia.licenses.services;

import book.manning.smia.licenses.clients.OrganizationDiscoveryClient;
import book.manning.smia.licenses.clients.OrganizationFeignClient;
import book.manning.smia.licenses.clients.OrganizationRestTemplateClient;
import book.manning.smia.licenses.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationFeignClient organizationFeignClient;

    @Autowired
    private OrganizationRestTemplateClient organizationRestTemplateClient;

    @Autowired
    private OrganizationDiscoveryClient organizationDiscoveryClient;

    public Organization getOrganizationByType(String clientType, String organizationId) {

        switch (clientType) {
            case "feign":
                return organizationFeignClient.getOrganization(organizationId);
            case "rest":
                return organizationRestTemplateClient.getOrganization(organizationId);
            case "discovery":
                return organizationDiscoveryClient.getOrganization(organizationId);
            default:
                return null;
        }
    }


}
