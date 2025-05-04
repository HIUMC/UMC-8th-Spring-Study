package umc.spring.repository.ReviewRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.*;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    public Review createReview(Long memberId, Long storeId, String title, Float rating) {
        Member member=jpaQueryFactory
                .selectFrom(QMember.member)
                .where(QMember.member.id.eq(memberId))
                .fetchOne();

        Store store=jpaQueryFactory
                .selectFrom(QStore.store)
                .where(QStore.store.id.eq(storeId))
                .fetchOne();

        Review review = Review.builder()
                        .title(title)
                        .rating(rating)
                        .member(member)
                        .store(store)
                        .build();

        entityManager.persist(review);

        return review;
    }

}
