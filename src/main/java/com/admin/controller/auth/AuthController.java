package com.admin.controller.auth;

import com.admin.domain.auth.Account;
import com.admin.dto.auth.RequestLoginDto;
import com.admin.dto.auth.ResponseLoginDto;
import com.admin.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDto> loginAccount(@RequestBody RequestLoginDto requestLoginDto, HttpServletResponse response, HttpServletRequest request){

        return ResponseEntity.ok().body(authService.loginAccount(requestLoginDto, response, request));
    }

    @GetMapping("/admin/list")
    public ResponseEntity<List<ResponseLoginDto>> getAdminList(){
        return ResponseEntity.ok().body(authService.getAdminList());
    }

}
