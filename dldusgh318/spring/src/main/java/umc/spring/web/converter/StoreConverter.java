package umc.spring.web.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.web.dto.StoreResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static StoreResponseDTO.ReviewPreviewDTO reviewPreviewDTO(Review review) {
        return StoreResponseDTO.ReviewPreviewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getRating())
                .createAt(review.getCreatedAt().toLocalDate())
                .body(review.getTitle())
                .build();
    }

    public static StoreResponseDTO.ReviewPreviewListDTO reviewPreviewListDTO(Page<Review> reviews) {
        List<StoreResponseDTO.ReviewPreviewDTO> reviewPreviewDTOS = reviews.stream()
                .map(StoreConverter::reviewPreviewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreviewListDTO.builder()
                .isLast(reviews.isLast())
                .isFirst(reviews.isFirst())
                .totalPage(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .listSize(reviewPreviewDTOS.size())
                .reviewList(reviewPreviewDTOS)
                .build();
    }
}
