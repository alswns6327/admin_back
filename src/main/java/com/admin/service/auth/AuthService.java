package com.admin.service.auth;

import com.admin.domain.auth.Account;
import com.admin.dto.auth.req.RequestLoginDto;
import com.admin.repository.auth.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthRepository authRepository;

    public Account loginAccount(RequestLoginDto requestLoginDto){
        return authRepository.findByAdminIdAndPassword(requestLoginDto.getAdminId(), requestLoginDto.getPassword())
                .orElseThrow(() -> new IllegalArgumentException("not found: " + requestLoginDto.getAdminId()));
    }

}
