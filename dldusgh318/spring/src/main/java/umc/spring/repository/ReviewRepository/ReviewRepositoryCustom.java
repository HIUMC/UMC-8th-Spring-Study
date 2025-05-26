package umc.spring.repository.ReviewRepository;

import umc.spring.domain.Review;

public interface ReviewRepositoryCustom {

    Review createReview(Long memberId, Long storeId, String title, Float rating);
}
