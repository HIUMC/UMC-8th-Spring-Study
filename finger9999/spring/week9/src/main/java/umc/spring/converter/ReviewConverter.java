package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.QMember;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.Review.ReviewRequestDTO;
import umc.spring.web.dto.Review.ReviewResponseDTO;

public class ReviewConverter {

    public static Review toStoreReview(ReviewRequestDTO.AddReviewDto request, Store store, Member member) {
        return Review.builder()
                .store(store)
                .body(request.getBody())
                .score(request.getScore())
                .member(member)
                .build();
    }

    public static ReviewResponseDTO.AddReviewResultDTO toAddResultDTO(Review review) {
        return ReviewResponseDTO.AddReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}