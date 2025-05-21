package umc.spring.repository.ReviewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewRepositoryCustom {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM MemberAgree ma WHERE ma.member.id = :memberId")
    void deleteAllByMemberId(@Param("reviewId") Long memberId);

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
}
