package umc.spring.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    Page<Mission> findAllByStoreId(Long StoreId, Pageable pageable);
}
