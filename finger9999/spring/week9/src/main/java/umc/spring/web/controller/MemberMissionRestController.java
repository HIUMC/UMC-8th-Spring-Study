package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMissionService.MemberMissionCommandService;
import umc.spring.service.MemberMissionService.MemberMissionQueryService;
import umc.spring.validation.annotation.ValidPage;
import umc.spring.web.dto.MemberMission.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMission.MemberMissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MemberMissionRestController {

    private final MemberMissionCommandService memberMissionCommandService;
    private final MemberMissionQueryService memberMissionQueryService;

    @PostMapping("/{missionId}/challenge")
    public ApiResponse<String> challengeMission(
            @PathVariable Long missionId,
            @RequestBody @Valid MemberMissionRequestDTO.ChallengeDto request) {

        memberMissionCommandService.challengeMission(missionId, request.getMemberId());
        return ApiResponse.onSuccess("미션 도전이 완료되었습니다.");
    }

    @GetMapping("/{memberId}/missions/challenging")
    @Operation(summary = "진행중인 미션 목록 조회", description = "회원이 도전중인 미션 목록을 페이징 조회합니다.")
    public ApiResponse<MemberMissionResponseDTO.MyChallengingMissionListDTO> getMissionList(
            @PathVariable(name = "memberId") @Valid Long memberId,
            @RequestParam(name = "page") @ValidPage Integer page
    ) {
        Page<MemberMission> myMissionList = memberMissionQueryService.getMissionList(memberId, page - 1);
        return ApiResponse.onSuccess(MemberMissionConverter.toMyChallengingMissionListDTO(myMissionList));
    }

    @PatchMapping("/{memberId}/missions/{missionId}/complete")
    @Operation(summary = "진행 중인 미션 완료 API", description = "특정 회원의 특정 미션을 완료 상태로 변경합니다.")
    public ApiResponse<?> completeMission(
            @PathVariable(name = "memberId") @Valid Long memberId,
            @PathVariable(name = "missionId") @Valid Long missionId
    ) {
        memberMissionQueryService.completeMission(memberId, missionId);
        return ApiResponse.onSuccess("미션이 완료되었습니다.");
    }
}


