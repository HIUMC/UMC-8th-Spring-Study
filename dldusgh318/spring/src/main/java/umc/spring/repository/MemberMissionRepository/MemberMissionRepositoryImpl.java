package umc.spring.repository.MemberMissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QMission;
import umc.spring.domain.QStore;
import umc.spring.domain.Region;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.QMemberMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMemberMission memberMission=QMemberMission.memberMission;

    @Override
    public List<MemberMission> findAllByMemberIdAndMissionStatus(Long memberId, MissionStatus missionStatus, Long cursorId, int limit){
        BooleanBuilder booleanBuilder=new BooleanBuilder();
        booleanBuilder.and(memberMission.id.eq(memberId));

        if(missionStatus==MissionStatus.CHALLENGING){
            booleanBuilder.and(memberMission.status.eq(MissionStatus.CHALLENGING));
        } else  {
            booleanBuilder.and(memberMission.status.eq(MissionStatus.COMPLETE));
        }

        if(cursorId!=null && cursorId != 0){
            booleanBuilder.and(memberMission.id.lt(cursorId));
        }

        List<MemberMission> memberMissions=jpaQueryFactory
                .selectFrom(memberMission)
                .where(booleanBuilder)
                .orderBy(memberMission.createdAt.desc())
                .limit(limit+1)
                .fetch();

        return memberMissions;
    }

    @Override
    public Long countCompletedAllByMemberIdAndRegion(Long memberId, Region region) {
        BooleanBuilder booleanBuilder=new BooleanBuilder();

        QMemberMission memberMission=QMemberMission.memberMission;
        QMission qMission=QMission.mission;
        QStore qStore=QStore.store;

        booleanBuilder.and(memberMission.member.id.eq(memberId));
        booleanBuilder.and(memberMission.status.eq(MissionStatus.COMPLETE));

        return jpaQueryFactory
                .selectFrom(memberMission)
                .join(memberMission.mission, qMission)
                .join(qMission.store, qStore)
                .where(qStore.region.eq(region))
                .fetchCount();
    }
}
