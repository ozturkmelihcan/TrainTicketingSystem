package com.melihcan.manager;

import com.melihcan.dto.request.ActivateRequestDto;
import com.melihcan.dto.request.UserSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.melihcan.constants.ApiUrls.ACTIVATESTATUS;

@FeignClient(name = "user-userprofile",decode404 = true,url = "http://localhost:9091/api/v1/user")
public interface IUserProfileManager {

    @PostMapping("create")
    ResponseEntity<Boolean>createUser(@RequestBody UserSaveRequestDto dto);

    @PostMapping(ACTIVATESTATUS+"/{authid}")
    ResponseEntity<Boolean>activateStatus(@PathVariable Long authid);
}
