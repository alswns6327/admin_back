package com.admin.repository.code;

import com.admin.domain.code.AdminCodeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminCodeGroupRepository extends JpaRepository<AdminCodeGroup, Long> {

    List<AdminCodeGroup> findByDelYn(int delYn);

}
