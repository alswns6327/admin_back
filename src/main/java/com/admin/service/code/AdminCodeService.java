package com.admin.service.code;

import com.admin.domain.code.AdminCode;
import com.admin.domain.code.AdminCodeGroup;
import com.admin.dto.code.RequestCodeDto;
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
        AdminCodeGroup adminCodeGroup = adminCodeGroupRepository.findById(requestCodeGroupDto.getId())
                .map(entity -> entity.update(requestCodeGroupDto))
                .orElse(new AdminCodeGroup(requestCodeGroupDto));
        adminCodeGroupRepository.save(adminCodeGroup);
        return getCodeGroupList();
    }

    public List<ResponseCodeDto> saveCode(RequestCodeDto requestCodeDto) {

        AdminCode adminCode = adminCodeRepository.findById(requestCodeDto.getId())
                .map(entity -> entity.update(requestCodeDto))
                .orElseGet(() -> makeNewAdminCode(requestCodeDto));
        adminCodeRepository.save(adminCode);

        return getCodeList(requestCodeDto.getCodeGroupId());
    }

    public AdminCode makeNewAdminCode(RequestCodeDto requestCodeDto){
        AdminCodeGroup adminCodeGroup = adminCodeGroupRepository.findById(requestCodeDto.getCodeGroupId())
                .orElseThrow(() -> new IllegalArgumentException("코드 그룹을 찾지 못하였음 id : " + requestCodeDto.getCodeGroupId()));

        return new AdminCode(requestCodeDto, adminCodeGroup);
    }

    public List<ResponseCodeGroupDto> removeCodeGroup(Long codeGroupId) {
        AdminCodeGroup adminCodeGroup = adminCodeGroupRepository.findById(codeGroupId)
                .orElseThrow(() -> new IllegalArgumentException("코드 그룹을 찾지 못하였음 id : " + codeGroupId));

        adminCodeGroup.getAdminCodeList().stream()
                .forEach(entity -> {entity.remove(); adminCodeRepository.save(entity);});

        adminCodeGroup.remove();
        adminCodeGroupRepository.save(adminCodeGroup);

        return getCodeGroupList();
    }

    public List<ResponseCodeDto> removeCode(Long codeId) {
        AdminCode adminCode = adminCodeRepository.findById(codeId)
                .orElseThrow(() -> new IllegalArgumentException("코드를 찾지 못하였음 : " + codeId));

        adminCode.remove();
        adminCodeRepository.save(adminCode);

        return getCodeList(adminCode.getAdminCodeGroup().getId());
    }
}
