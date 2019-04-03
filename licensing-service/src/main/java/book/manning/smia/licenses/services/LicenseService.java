package book.manning.smia.licenses.services;

import book.manning.smia.licenses.model.License;
import book.manning.smia.licenses.model.Organization;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LicenseService {

    @Autowired
    private OrganizationService organizationService;

    public License getLicense(String licenseId) {
        Organization o1 = organizationService.getOrganizationByType("rest", "123");
        Organization o2 = organizationService.getOrganizationByType("feign", "344");
        Organization o3 = organizationService.getOrganizationByType("discovery", "456");
        return License.builder().id(licenseId)
                .organizationId(UUID.randomUUID().toString())
                .productName("Test Product Name")
                .licenseType("PerSeat")
                .build();
    }

    public void saveLicense(License license) {

    }

    public void updateLicense(License license) {

    }

    public void deleteLicense(License license) {

    }
}
