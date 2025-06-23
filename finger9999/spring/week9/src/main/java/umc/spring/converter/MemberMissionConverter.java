package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMission.MemberMissionResponseDTO;

import java.util.List;

public class MemberMissionConverter {

    public static MemberMissionResponseDTO.MyChallengingMissionDTO toMyChallengingMissionDTO(MemberMission mm) {
        Mission mission = mm.getMission();
        return MemberMissionResponseDTO.MyChallengingMissionDTO.builder()
                .missionSpec(mission.getMissionSpec())
                .deadline(mission.getDeadline())
                .reward(mission.getReward())
                .build();
    }

    public static MemberMissionResponseDTO.MyChallengingMissionListDTO toMyChallengingMissionListDTO(Page<MemberMission> missionList) {
        List<MemberMissionResponseDTO.MyChallengingMissionDTO> myChallengingMissionDTOList = missionList.stream()
                .map(MemberMissionConverter::toMyChallengingMissionDTO)
                .toList();

        return MemberMissionResponseDTO.MyChallengingMissionListDTO.builder()
                .missionList(myChallengingMissionDTOList)
                .listSize(myChallengingMissionDTOList.size())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .build();
    }
}
