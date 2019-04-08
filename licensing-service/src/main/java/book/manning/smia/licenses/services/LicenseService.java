package book.manning.smia.licenses.services;

import book.manning.smia.licenses.model.License;
import book.manning.smia.licenses.model.Organization;
import book.manning.smia.licenses.utils.UserContextHolder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@Log
public class LicenseService {

    @Autowired
    private OrganizationService organizationService;

    @HystrixCommand(fallbackMethod = "buildFallbackLicense"
        , commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="9000")}
        , threadPoolKey = "licenses"
        , threadPoolProperties = {@HystrixProperty(name="coreSize", value="30"), @HystrixProperty(name="maxQueueSize", value="10")}
    )
    public License getLicense(String licenseId) {
        log.info("LicenseService Correlation Id: " + UserContextHolder.getContext().getCorrelationId());
        Organization o1 = organizationService.getOrganizationByType("rest", "123");
        Organization o2 = organizationService.getOrganizationByType("feign", "344");
        Organization o3 = organizationService.getOrganizationByType("discovery", "456");
        Random rand = new Random();
        int randNum = rand.nextInt(3) + 1;
        if (randNum == 3) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return License.builder().id(licenseId)
                .organizationId(UUID.randomUUID().toString())
                .productName("Test Product Name")
                .licenseType("PerSeat")
                .build();
    }

    public License buildFallbackLicense(String licenseId) {
        return License.builder().id("0").build();
    }

    public void saveLicense(License license) {

    }

    public void updateLicense(License license) {

    }

    public void deleteLicense(License license) {

    }
}
