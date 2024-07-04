package com.admin.repository.auth;

import com.admin.domain.auth.Account;
import com.admin.dto.auth.req.RequestLoginDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAdminIdAndPassword(String adminId, String password);

}
