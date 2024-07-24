package com.admin.service.auth;

import com.admin.config.jwt.TokenProvider;
import com.admin.domain.auth.Account;
import com.admin.domain.auth.RefreshToken;
import com.admin.dto.auth.RequestLoginDto;
import com.admin.dto.auth.ResponseLoginDto;
import com.admin.repository.auth.AuthRepository;
import com.admin.repository.auth.RefreshTokenRepository;
import com.admin.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;

    public ResponseLoginDto loginAccount(RequestLoginDto requestLoginDto, HttpServletResponse response, HttpServletRequest request){
        Account account = authRepository.findByAdminId(requestLoginDto.getAdminId())
                .orElseThrow(() -> new IllegalArgumentException("not found: " + requestLoginDto.getAdminId()));

        if(bCryptPasswordEncoder.matches(requestLoginDto.getPassword(), account.getPassword())){
            String adminId = account.getAdminId();
            String accessToken = tokenProvider.generateToken(adminId, tokenProvider.ACCESS_TOKEN_EXPIRED);
            String refreshToken = tokenProvider.generateToken(adminId, tokenProvider.REFRESH_TOKEN_EXPIRED);

            long refreshTokenId = account.getRefreshToken() == null ? -1 : account.getRefreshToken().getId();
            RefreshToken newRefreshtoken = refreshTokenRepository.findById(refreshTokenId)
                    .map(entity -> entity.update(refreshToken))
                    .orElse(new RefreshToken(refreshToken));

            refreshTokenRepository.save(newRefreshtoken);

            account.setRefreshToken(newRefreshtoken);
            authRepository.save(account);

            CookieUtil.addCookie(response, tokenProvider.REFRESH_TOKEN, refreshToken, (int)tokenProvider.REFRESH_TOKEN_EXPIRED.toSeconds());


            return new ResponseLoginDto(account, accessToken);
        } else{
            return new ResponseLoginDto();
        }
    }

    public List<ResponseLoginDto> getAdminList() {
        return authRepository.findAll().stream().map(ResponseLoginDto::new).collect(Collectors.toList());
    }
}
