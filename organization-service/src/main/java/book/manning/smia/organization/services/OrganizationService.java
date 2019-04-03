package book.manning.smia.organization.services;

import book.manning.smia.organization.model.Organization;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    public Organization getOrganization(String organizationId) {
        return Organization.builder().id(organizationId).name("unsw").build();
    }

}
