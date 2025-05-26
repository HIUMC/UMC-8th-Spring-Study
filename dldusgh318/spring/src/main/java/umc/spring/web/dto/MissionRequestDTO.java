package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.validation.annotation.ExistStore;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class createMissionDTO{

        private Integer reward;
        private LocalDate deadline;
        private String missionSpec;
    }
}
