package umc.spring.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class MissionResponseDTO {

    @Getter
    @Builder
    public static class MissionDTO {
        Long missionId;
        Integer reward;
        LocalDate deadline;
        String missionSpec;
    }

    @Getter
    @Builder
    public static class MissionListDTO {
        List<MissionDTO> missions;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
}
