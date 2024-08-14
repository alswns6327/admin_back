package com.admin.domain.code;

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
public class AdminCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADMIN_CODE_ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "ADMIN_CODE_NAME")
    private String adminCodeName;

    @Column(name = "ADMIN_CODE")
    private String adminCode;

    @Column(name = "DEL_YN", nullable = false)
    private int delYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADMIN_CODE_GROUP_ID")
    private AdminCodeGroup adminCodeGroup;


}
