package umc.spring.web.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.ReviewImage;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {

    public static Review toEntity(ReviewRequestDTO.createReviewDTO request, Store store, Member member) {
        Review review = Review.builder()
                .title(request.getTitle())
                .rating(request.getRating())
                .store(store)
                .member(member)
                .build();

        if (request.getImageUrls() != null) {
            request.getImageUrls().forEach(url -> review.getReviewImageList().add(
                    ReviewImage.builder()
                            .imageUrl(url)
                            .review(review)
                            .build()
            ));
        }

        return review;
    }

    public static ReviewResponseDTO.ReviewResultDTO toDTO(Review review){
        return ReviewResponseDTO.ReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
