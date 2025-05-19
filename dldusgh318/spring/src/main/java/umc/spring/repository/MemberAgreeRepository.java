package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umc.spring.domain.mapping.MemberAgree;

@Repository
public interface MemberAgreeRepository extends JpaRepository<MemberAgree, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM MemberAgree ma WHERE ma.member.id = :memberId")
    void deleteAllByMemberId(@Param("reviewId") Long memberId);
}
