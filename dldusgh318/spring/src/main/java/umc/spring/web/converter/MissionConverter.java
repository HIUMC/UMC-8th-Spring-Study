package umc.spring.web.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO;

public class MissionConverter {

    public static Mission toMission(MissionRequestDTO.createMissionDTO dto, Store store){
        Mission mission= Mission.builder()
                .missionSpec(dto.getMissionSpec())
                .reward(dto.getReward())
                .deadline(dto.getDeadline())
                .store(store)
                .build();

        return mission;
    }
}
