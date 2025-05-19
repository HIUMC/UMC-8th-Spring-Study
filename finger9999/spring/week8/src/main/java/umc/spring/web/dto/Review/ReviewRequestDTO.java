package umc.spring.web.dto.Review;

import jakarta.validation.constraints.*;
import lombok.Getter;
import umc.spring.validation.annotation.ExistStore;

public class ReviewRequestDTO {

    @Getter
    public static class AddDto {
        @ExistStore
        Long storeId;

        @NotBlank(message = "리뷰 내용을 입력해주세요.")
        String title;

        @Min(value = 1, message = "평점은 최소 1점입니다.")
        @Max(value = 5, message = "평점은 최대 5점입니다.")
        float score;
    }
}

