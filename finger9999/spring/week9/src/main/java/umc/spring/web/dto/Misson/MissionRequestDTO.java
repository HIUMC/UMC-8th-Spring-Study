package umc.spring.web.dto.Misson;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionRequestDTO {

    @Getter
    public static class AddDto {

        @NotBlank(message = "미션 설명을 입력해주세요.")
        String missionSpec;

        @NotNull(message = "보상 포인트를 입력해주세요")
        @Min(1)
        Integer reward;

        @NotNull(message = "마감일을 입력해주세요")
        @Future(message = "마감일은 미래여야 합니다.")
        LocalDate deadline;
    }
}
