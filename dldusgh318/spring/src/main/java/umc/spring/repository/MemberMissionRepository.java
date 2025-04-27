package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umc.spring.domain.mapping.MemberMission;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM MemberAgree ma WHERE ma.member.id = :memberId")
    void deleteAllByMemberId(@Param("memberId") Long memberId);
}
