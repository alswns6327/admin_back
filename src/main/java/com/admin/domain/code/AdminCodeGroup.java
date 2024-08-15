package com.admin.domain.code;

import com.admin.dto.code.RequestCodeGroupDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin_code_group")
public class AdminCodeGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADMIN_CODE_GROUP_ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "ADMIN_CODE_GROUP_NAME", nullable = false)
    private String adminCodeGroupName;

    @Column(name = "DEL_YN", nullable = false)
    private int delYn;

    @OneToMany(mappedBy = "adminCodeGroup", fetch = FetchType.LAZY)
    private List<AdminCode> adminCodeList;

    public AdminCodeGroup(RequestCodeGroupDto requestCodeGroupDto) {
        this.adminCodeGroupName = requestCodeGroupDto.getCodeGroupName();
        this.delYn = 1;
    }

    public AdminCodeGroup update(RequestCodeGroupDto requestCodeGroupDto) {
        this.adminCodeGroupName = requestCodeGroupDto.getCodeGroupName();
        return this;
    }

    public void remove() {
        this.delYn = 0;
    }
}
