package com.admin.service.code;

import com.admin.dto.code.RequestCodeGroupDto;
import com.admin.dto.code.ResponseCodeDto;
import com.admin.dto.code.ResponseCodeGroupDto;
import com.admin.repository.code.AdminCodeGroupRepository;
import com.admin.repository.code.AdminCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminCodeService {

    private final AdminCodeRepository adminCodeRepository;
    private final AdminCodeGroupRepository adminCodeGroupRepository;

    public List<ResponseCodeGroupDto> getCodeGroupList() {
        return adminCodeGroupRepository.findByDelYn(1).stream().map(ResponseCodeGroupDto::new).collect(Collectors.toList());
    }

    public List<ResponseCodeDto> getCodeList(Long codeGroupId) {
        return adminCodeRepository.findByAdminCodeGroupIdAndDelYn(codeGroupId, 1).stream().map(ResponseCodeDto::new).collect(Collectors.toList());
    }

    public List<ResponseCodeGroupDto> saveCodeGroup(RequestCodeGroupDto requestCodeGroupDto) {
        //adminCodeGroupRepository.save()

        return getCodeGroupList();
    }
}
