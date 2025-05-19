package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.domain.Review;
import umc.spring.service.ReviewService;
import umc.spring.web.converter.ReviewConverter;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/{storeId}/{memberId}")
    public ApiResponse<ReviewResponseDTO.ReviewResultDTO> createReview(
            @PathVariable Long storeId,
            @Valid @RequestBody ReviewRequestDTO.createReviewDTO dto,
            @PathVariable Long memberId){
                Review review = reviewService.createReview(storeId,dto,memberId);

                return ApiResponse.onSuccess(ReviewConverter.toDTO(review));
    }
}
