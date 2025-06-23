package umc.spring.service.MemberMissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionQueryService {

    Page<MemberMission> getMissionList(Long memberId, Integer page);

    void completeMission(Long memberId, Long missionId);
}
