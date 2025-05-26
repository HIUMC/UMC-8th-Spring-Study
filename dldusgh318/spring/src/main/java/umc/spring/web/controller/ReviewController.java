package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.domain.Review;
import umc.spring.service.ReviewService;
import umc.spring.validation.annotation.PageNumber;
import umc.spring.web.converter.ReviewConverter;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
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


    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "한 페이지당 10개, 1-based page 파라미터를 사용합니다.")
    @GetMapping("/{memberId}")
    public ApiResponse<ReviewResponseDTO.ReviewListDTO> getMyReviews(
            @PathVariable Long memberId,
            @Parameter(description = "페이지 번호(1 이상)", example = "1")
            @PageNumber @RequestParam Integer page) {

        return ApiResponse.onSuccess(reviewService.getMyReviews(memberId,page));
    }
}
