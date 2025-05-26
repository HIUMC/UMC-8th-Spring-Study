package umc.spring.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Mission;
import umc.spring.domain.QMission;
import umc.spring.domain.QStore;
import umc.spring.domain.Region;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryCustomImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Mission> findAllByRegion(Region region, Long cursorId, int limit) {

        QMission qMission = QMission.mission;
        QStore qStore = QStore.store;
        BooleanBuilder booleanBuilder=new BooleanBuilder();

        if(cursorId!=null && cursorId != 0){
            booleanBuilder.and(qMission.id.lt(cursorId));
        }

        return jpaQueryFactory
                .selectFrom(qMission)
                .join(qMission.store, qStore)
                .where(qMission.store.region.eq(region))
                .orderBy(qMission.createdAt.desc())
                .limit(limit+1)
                .fetch();
    }

}
