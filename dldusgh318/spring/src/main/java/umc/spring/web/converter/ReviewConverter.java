package umc.spring.web.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.ReviewImage;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static StoreResponseDTO.ReviewPreviewDTO reviewPreViewDTO(Review review){
        return null;
    }
    public static StoreResponseDTO.ReviewPreviewListDTO reviewPreViewListDTO(List<Review> reviewList){
        return null;
    }

    public static ReviewResponseDTO.ReviewDTO toReviewDTO(Review review){
        return ReviewResponseDTO.ReviewDTO.builder()
                .reviewId(review.getId())
                .rating(review.getRating())
                .title(review.getTitle())
                .images(
                        review.getReviewImageList().stream()
                                .map(img->img.getImageUrl())
                                .collect(Collectors.toList())
                )
                .build();
    }

    public static ReviewResponseDTO.ReviewListDTO toReviewListDTO(Page<Review> reviewList){
        List<ReviewResponseDTO.ReviewDTO> reviewDTOS= reviewList.stream()
                .map(ReviewConverter::toReviewDTO).collect(Collectors.toList());

        return ReviewResponseDTO.ReviewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewDTOS.size())
                .reviews(reviewDTOS)
                .build();
    }
}
