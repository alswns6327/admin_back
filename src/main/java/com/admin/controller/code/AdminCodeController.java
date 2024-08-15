package com.admin.controller.code;

import com.admin.dto.code.RequestCodeDto;
import com.admin.dto.code.RequestCodeGroupDto;
import com.admin.dto.code.ResponseCodeDto;
import com.admin.dto.code.ResponseCodeGroupDto;
import com.admin.service.code.AdminCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AdminCodeController {

    private final AdminCodeService adminCodeService;

    @GetMapping("/code/group")
    public ResponseEntity<List<ResponseCodeGroupDto>> getCodeGroupList() {
        return ResponseEntity.ok().body(adminCodeService.getCodeGroupList());
    }

    @GetMapping("/code/{codeGroupId}")
    public ResponseEntity<List<ResponseCodeDto>> getCodeList(@PathVariable("codeGroupId") String codeGroupId) {
        return ResponseEntity.ok().body(adminCodeService.getCodeList(Long.parseLong(codeGroupId)));
    }

    @PostMapping("/code/group")
    public ResponseEntity<List<ResponseCodeGroupDto>> saveCodeGroup(@RequestBody RequestCodeGroupDto requestCodeGroupDto){
        return ResponseEntity.ok().body(adminCodeService.saveCodeGroup(requestCodeGroupDto));
    }

    @PostMapping("/code")
    public ResponseEntity<List<ResponseCodeDto>> saveCode(@RequestBody RequestCodeDto requestCodeDto){
        return ResponseEntity.ok().body(adminCodeService.saveCode(requestCodeDto));
    }

    @DeleteMapping("/code/group/{codeGroupId}")
    public ResponseEntity<List<ResponseCodeGroupDto>> removeCodeGroup(@PathVariable("codeGroupId") String codeGroupId){
        return ResponseEntity.ok().body(adminCodeService.removeCodeGroup(Long.parseLong(codeGroupId)));
    }

    @DeleteMapping("/code/group/{codeId}")
    public ResponseEntity<List<ResponseCodeDto>> removeCode(@PathVariable("codeId") String codeId){
        return ResponseEntity.ok().body(adminCodeService.removeCode(Long.parseLong(codeId)));
    }
}
