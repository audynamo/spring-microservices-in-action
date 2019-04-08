package book.manning.smia.licenses.controllers;

import book.manning.smia.licenses.model.License;
import book.manning.smia.licenses.services.LicenseService;
import book.manning.smia.licenses.utils.UserContextHolder;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/org/{organizationId}/licenses")
@Log
public class LicenseServiceController {
    @Autowired
    private LicenseService licenseService;

    @RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
    public License getLicense(@PathVariable("organizationId") String organizationId,
                              @PathVariable("licenseId") String licenseId) {
        log.info("LicenseServiceController Correlation Id: " + UserContextHolder.getContext().getCorrelationId());
        return licenseService.getLicense(licenseId);
    }

    @RequestMapping(value = "/{licenseId}", method = RequestMethod.POST)
    public String updateLicense(@PathVariable("licenseId") String licenseId) {
        return String.format("This is the put");
    }
}
