package com.admin.controller.auth;

import com.admin.domain.auth.Account;
import com.admin.dto.auth.req.RequestLoginDto;
import com.admin.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Account> loginAccount(@RequestBody RequestLoginDto requestLoginDto){

        return ResponseEntity.ok().body(authService.loginAccount(requestLoginDto));
    }


}