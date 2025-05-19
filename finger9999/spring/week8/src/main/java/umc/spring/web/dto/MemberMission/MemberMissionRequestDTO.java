package umc.spring.web.dto.MemberMission;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.AlreadyChallenged;

public class MemberMissionRequestDTO {

    @Getter
    public static class ChallengeDto {

        @NotNull(message = "회원 ID는 필수입니다.")
        @AlreadyChallenged  // 커스텀 어노테이션
        private Long memberId;
    }
}
