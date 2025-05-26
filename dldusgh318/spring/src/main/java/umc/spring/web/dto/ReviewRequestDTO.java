package umc.spring.web.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

public class ReviewRequestDTO {

    @Getter
    public static class createReviewDTO{
        @NotBlank(message = "제목은 필수입니다")
        private String title;

        @NotNull(message = "평점은 필수입니다")
        @DecimalMin(value = "0.0", inclusive = true)
        @DecimalMax(value = "5.0", inclusive = true)
        private Float rating;

        private List<String> imageUrls;
    }
}
