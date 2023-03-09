package com.melihcan.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static com.melihcan.constants.ApiUrls.ACTIVATESTATUS;

@FeignClient(name = "auth-user",decode404 = true,url = "http://localhost:8090/api/v1/auth")
public interface IAuthManager {

    @PostMapping(ACTIVATESTATUS+"/{authid}")
    ResponseEntity<Boolean>activateStatus(@PathVariable Long authid);
}
