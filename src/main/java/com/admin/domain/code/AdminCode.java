package com.admin.domain.code;

import com.admin.domain.common.CommonColumns1;
import com.admin.dto.code.RequestCodeDto;
import com.admin.util.Common;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin_code")
public class AdminCode extends CommonColumns1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADMIN_CODE_ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "ADMIN_CODE_NAME")
    private String adminCodeName;

    @Column(name = "ADMIN_CODE")
    private String adminCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADMIN_CODE_GROUP_ID")
    private AdminCodeGroup adminCodeGroup;

    public AdminCode(RequestCodeDto requestCodeDto, AdminCodeGroup adminCodeGroup) {
        this.adminCodeName = requestCodeDto.getCodeName();
        this.adminCode = requestCodeDto.getCode();
        this.adminCodeGroup = adminCodeGroup;
    }

    public AdminCode update(RequestCodeDto requestCodeDto) {
        this.adminCodeName = requestCodeDto.getCodeName();
        this.adminCode = requestCodeDto.getCode();
        return this;
    }
}
