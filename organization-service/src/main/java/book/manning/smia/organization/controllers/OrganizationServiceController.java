package book.manning.smia.organization.controllers;

import book.manning.smia.organization.model.Organization;
import book.manning.smia.organization.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/v1/organizations")
public class OrganizationServiceController {

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value="/{organizationId}", method=RequestMethod.GET)
    public Organization getOrganization(@PathVariable("organizationId") String organizationId) {
        return organizationService.getOrganization(organizationId);
    }
}
