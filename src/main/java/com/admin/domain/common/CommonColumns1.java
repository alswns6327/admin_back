package com.admin.domain.common;

import com.admin.domain.auth.Account;
import com.admin.domain.menu.Menu;
import com.admin.util.Common;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
public abstract class CommonColumns1 {

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FIRST_SAVE_DT", updatable = false, nullable = false)
    private LocalDateTime firstSaveDt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPDATE_DT")
    private LocalDateTime lastUpdateDt;

    @Column(name = "FIRST_SAVE_USER", updatable = false, nullable = false)
    private String firstSaveUser;

    @Column(name = "LAST_UPDATE_USER")
    private String lastUpdateUser;

    @Column(name="DEL_YN", nullable = false)
    private int delYn;

    public void remove(){
        this.lastUpdateDt = LocalDateTime.now();
        this.lastUpdateUser = Common.getAdminId();
        this.delYn = 0;
    }

    @PrePersist // 데이터 생성이 이루어질때 사전 작업
    public void prePersist() {
        this.firstSaveDt = LocalDateTime.now();
        this.firstSaveUser = Common.getAdminId();
        this.delYn = 1;
    }

    @PreUpdate // 데이터 수정이 이루어질때 사전 작업
    public void preUpdate() {
        this.lastUpdateDt = LocalDateTime.now();
        this.lastUpdateUser = Common.getAdminId();
    }
}
