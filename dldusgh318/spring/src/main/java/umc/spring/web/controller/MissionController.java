package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.service.MissionService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.IsNotChallengedMission;
import umc.spring.validation.annotation.PageNumber;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

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

    @IsNotChallengedMission
    @PostMapping("/{memberId}/{missionId}")
    public ApiResponse<?> challengeMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId) {
        missionService.challengeMission(missionId, memberId);
        return ApiResponse.onSuccess("미션 도전 성공");
    }

    @Operation(summary = "특정 가게의 미션 목록 조회",
            description = "가게 ID로 등록된 미션을 조회")
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.MissionListDTO> getStoreMissions(
            @PathVariable Long storeId,
            @Parameter(description = "페이지 번호(1 이상)", example = "1")
            @PageNumber @RequestParam Integer page) {

        return ApiResponse.onSuccess(
                missionService.getStoreMissions(storeId, page)
        );
    }

    @Operation(summary = "내가 진행중인 미션 목록 조회",
            description = "회원이 CHALLENGING 상태로 도전 중인 미션을 조회")
    @GetMapping("/members/{memberId}/missions/ongoing")
    public ApiResponse<MissionResponseDTO.MissionListDTO> getMyChallengingMissions(
            @PathVariable Long memberId,
            @Parameter(description = "페이지 번호(1 이상)", example = "1")
            @PageNumber @RequestParam Integer page) {

        return ApiResponse.onSuccess(
                missionService.getMyChallengingMissions(memberId, page)
        );
    }
}
