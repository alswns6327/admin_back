package com.admin.service.auth;

import com.admin.config.jwt.TokenProvider;
import com.admin.domain.auth.Account;
import com.admin.domain.auth.RefreshToken;
import com.admin.dto.auth.RequestLoginDto;
import com.admin.dto.auth.ResponseLoginDto;
import com.admin.repository.auth.AuthRepository;
import com.admin.repository.auth.RefreshTokenRepository;
import com.admin.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

            CookieUtil.addCookie(response, tokenProvider.REFRESH_TOKEN, refreshToken, (int)tokenProvider.REFRESH_TOKEN_EXPIRED.toSeconds(), true);


            return new ResponseLoginDto(account, accessToken);
        } else{
            return new ResponseLoginDto();
        }
    }

    public List<ResponseLoginDto> getAdminList() {
        return authRepository.findByDelYn(1).stream().map(ResponseLoginDto::new).collect(Collectors.toList());
    }

    public List<ResponseLoginDto> saveAdmin(RequestLoginDto requestLoginDto) {
        requestLoginDto.encrytPassword(bCryptPasswordEncoder);
        Account account = authRepository.findById(requestLoginDto.getId()).map(entity -> entity.update(requestLoginDto))
                        .orElse(new Account(requestLoginDto));
        authRepository.save(account);
        return getAdminList();
    }

    public String checkRefreshToken(HttpServletResponse response, HttpServletRequest request) {

        String refreshToken = CookieUtil.getCookie(request, tokenProvider.REFRESH_TOKEN);


        if(refreshToken == null || !tokenProvider.validToken(refreshToken)) return "login";
        else return tokenProvider.generateToken(tokenProvider.getAdminId(refreshToken), tokenProvider.ACCESS_TOKEN_EXPIRED);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
            CookieUtil.deleteCookie(request, response, tokenProvider.REFRESH_TOKEN);
        }catch (Exception e){
            throw new Exception("로그아웃 실패");
        }
    }

    public List<ResponseLoginDto> removeAdmin(long id) {

        Account account = authRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("삭제 실패 : " + id));

        account.remove();

        authRepository.save(account);

        return getAdminList();
    }
}
