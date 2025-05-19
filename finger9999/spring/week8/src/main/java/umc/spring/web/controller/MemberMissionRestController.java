package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMissionService.MemberMissionCommandService;
import umc.spring.web.dto.MemberMission.MemberMissionRequestDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MemberMissionRestController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/{missionId}/challenge")
    public ApiResponse<String> challengeMission(
            @PathVariable Long missionId,
            @RequestBody @Valid MemberMissionRequestDTO.ChallengeDto request) {

        memberMissionCommandService.challengeMission(missionId, request.getMemberId());
        return ApiResponse.onSuccess("미션 도전이 완료되었습니다.");
    }
}

