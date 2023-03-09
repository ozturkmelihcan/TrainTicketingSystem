package com.melihcan.controller;

import com.melihcan.dto.request.UserSaveRequestDto;
import com.melihcan.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userprofile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping("/save")
    public ResponseEntity<Boolean>save(@RequestBody UserSaveRequestDto dto){
      return   ResponseEntity.ok(userProfileService.saveDto(dto));
    }
}
