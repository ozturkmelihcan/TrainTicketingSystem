package com.melihcan.controller;

import com.melihcan.dto.request.ActivateRequestDto;
import com.melihcan.dto.request.DoLoginRequestDto;
import com.melihcan.dto.request.RegisterRequestDto;
import com.melihcan.dto.response.RegisterResponseDto;
import com.melihcan.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import  static com.melihcan.constants.ApiUrls.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {

    private final AuthService authService;
    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto dto){
        return ResponseEntity.ok(authService.register(dto));
    }
    @PostMapping(LOGIN)
    public ResponseEntity<String>doLogin(@RequestBody DoLoginRequestDto dto){
        return ResponseEntity.ok(authService.doLogin(dto));
    }

    @PostMapping("/activatestatus")
    public ResponseEntity<Boolean>activateStatus(@RequestBody ActivateRequestDto dto){
        return ResponseEntity.ok(authService.activateStatus(dto));
    }
}
