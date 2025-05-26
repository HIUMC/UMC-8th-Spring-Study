package umc.spring.web.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.List;

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

    private static MissionResponseDTO.MissionDTO toDTO(Mission m) {
        return MissionResponseDTO.MissionDTO.builder()
                .missionId(m.getId())
                .reward(m.getReward())
                .deadline(m.getDeadline())
                .missionSpec(m.getMissionSpec())
                .build();
    }

    public static MissionResponseDTO.MissionListDTO toMissionListDTO(Page<Mission> missions) {
        List<MissionResponseDTO.MissionDTO> dtoList = missions.getContent().stream()
                .map(MissionConverter::toDTO)
                .toList();

        return MissionResponseDTO.MissionListDTO.builder()
                .missions(dtoList)
                .listSize(missions.getSize())
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .build();
    }
}
