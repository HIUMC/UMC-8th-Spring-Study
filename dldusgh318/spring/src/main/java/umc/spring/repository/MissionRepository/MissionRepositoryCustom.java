package umc.spring.repository.MissionRepository;

import umc.spring.domain.Mission;
import umc.spring.domain.Region;

import java.util.List;

public interface MissionRepositoryCustom {

    List<Mission> findAllByRegion(Region region, Long cursorId, int limit);

}
