package umc.spring.repository.MemberMissionRepository;


import umc.spring.domain.Region;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

import java.util.List;

public interface MemberMissionRepositoryCustom {

    List<MemberMission> findAllByMemberIdAndMissionStatus(Long memberId, MissionStatus missionStatus, Long cursorId, int limit);

    Long countCompletedAllByMemberIdAndRegion(Long memberId, Region region);
}
