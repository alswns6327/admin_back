package com.admin.repository.code;

import com.admin.domain.code.AdminCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminCodeRepository extends JpaRepository<AdminCode, Long> {

    List<AdminCode> findByAdminCodeGroupIdAndDelYn(Long codeGroupId, int delYn);

}
