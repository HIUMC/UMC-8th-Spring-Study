package umc.spring.service.MissionService;

import umc.spring.domain.Mission;
import umc.spring.web.dto.Misson.MissionRequestDTO;

public interface MissionCommandService {
    Mission addMission(Long storeId, MissionRequestDTO.AddDto request);
}
