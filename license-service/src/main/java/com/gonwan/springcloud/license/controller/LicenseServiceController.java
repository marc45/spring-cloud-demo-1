package com.gonwan.springcloud.license.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gonwan.springcloud.license.model.License;
import com.gonwan.springcloud.license.service.LicenseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "License Controller", description = "Defines license operations")
@RestController
@RequestMapping("/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

    @Autowired
    private LicenseService licenseService;

    /*
     * # curl http://localhost:8080/v1/organizations/e254f8c-c442-4ebe-a82a-e2fc1d1ff78a/licenses
     * # curl -u eagleeye:thisissecret http://localhost:8901/auth/oauth/token -X POST -d "grant_type=password&scope=webclient&username=user&password=password1"
     * {"access_token":"67cded20-2433-4b51-887e-6cfb16561cd5","token_type":"bearer","refresh_token":"2645a4d8-fc53-4c72-88e7-6707b97ca2e4","expires_in":43199,"scope":"webclient"}
     * # curl -H "Authorization: Bearer 67cded20-2433-4b51-887e-6cfb16561cd5" http://localhost:8080/v1/organizations/e254f8c-c442-4ebe-a82a-e2fc1d1ff78a/licenses
     */
    @ApiOperation("Get licenses by organization ID")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
    })
    @GetMapping
    public List<License> getLicenses(@PathVariable("organizationId") String organizationId) {
        return licenseService.getLicenses(organizationId);
    }

    /*
     * # curl -H "Authorization: Bearer 67cded20-2433-4b51-887e-6cfb16561cd5" http://localhost:8080/v1/organizations/e254f8c-c442-4ebe-a82a-e2fc1d1ff78a/licenses/f3831f8c-c338-4ebe-a82a-e2fc1d1ff78a
     */
    @ApiOperation("Get license by organization ID and license ID")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
    })
    @GetMapping("/{licenseId}")
    public License getLicense(@PathVariable("organizationId") String organizationId, @PathVariable("licenseId") String licenseId) {
        return licenseService.getLicense(organizationId, licenseId);
    }

    /*
     * # curl -H "Authorization: Bearer 67cded20-2433-4b51-887e-6cfb16561cd5" -H "Content-Type: application/json" http://localhost:8080/v1/organizations/e254f8c-c442-4ebe-a82a-e2fc1d1ff78a/licenses \
     *        -X POST -d '{ "organizationId": "e254f8c-c442-4ebe-a82a-e2fc1d1ff78a", "productName": "productName1", "licenseType": "licenseType1", "licenseMax": 200, "licenseAllocated": 10 }'
     */
    @ApiOperation("Save license")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
    })
    @PostMapping
    public void saveLicense(@RequestBody License license) {
        licenseService.saveLicense(license);
    }

    /*
     * # curl -H "Authorization: Bearer 67cded20-2433-4b51-887e-6cfb16561cd5" -H "Content-Type: application/json" http://localhost:8080/v1/organizations/e254f8c-c442-4ebe-a82a-e2fc1d1ff78a/licenses/0c107783-ba3d-44b0-8a25-40c095712b2c \
     *        -X PUT -d '{ "organizationId": "e254f8c-c442-4ebe-a82a-e2fc1d1ff78a", "productName": "productName2", "licenseType": "licenseType2", "licenseMax": 200, "licenseAllocated": 10 }'
     */
    @ApiOperation("Update license")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
    })
    @PutMapping("/{licenseId}")
    public void updateLicense(@PathVariable("licenseId") String licenseId, @RequestBody License license) {
        licenseService.updateLicense(licenseId, license);
    }

    /*
     * # curl -H "Authorization: Bearer 67cded20-2433-4b51-887e-6cfb16561cd5" -H "Content-Type: application/json" http://localhost:8080/v1/organizations/4479bbed-8a3d-42f1-99d6-1c0c747dc381/licenses/a9f3bf65-8d30-416c-a6bd-86863eab44d6 -X DELETE
     */
    @ApiOperation("Delete license")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "token", dataType = "string", paramType = "header")
    })
    @DeleteMapping("/{licenseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLicense(@PathVariable("licenseId") String licenseId) {
         licenseService.deleteLicense(licenseId);
    }

}
