package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.Review.ReviewRequestDTO;
import umc.spring.web.dto.Review.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {

    public static Review toStoreReview(ReviewRequestDTO.AddDto request, Store store) {
        return Review.builder()
                .store(store)
                .title(request.getTitle())
                .score(request.getScore())
                .build();
    }

    public static ReviewResponseDTO.AddResultDTO toAddResultDTO(Review review) {
        return ReviewResponseDTO.AddResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}