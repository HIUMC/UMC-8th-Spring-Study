package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.service.MissionService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.IsChallengedMission;
import umc.spring.web.dto.MissionRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/{storeId}")
    public ApiResponse<?> addMission(
            @PathVariable @ExistStore Long storeId,
            @Valid @RequestBody MissionRequestDTO.createMissionDTO request) {
        missionService.addMission(storeId, request);
        return ApiResponse.onSuccess("미션 추가 성공");
    }

    @PostMapping("/{memberId}/{missionId}")
    public ApiResponse<?> challengeMission(
            @PathVariable Long memberId,
            @PathVariable @IsChallengedMission Long missionId) {
        missionService.challengeMission(missionId, memberId);
        return ApiResponse.onSuccess("미션 도전 성공");
    }
}
