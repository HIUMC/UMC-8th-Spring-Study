package umc.spring.web.dto.Store;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class StoreRequestDTO {
    @Getter
    public static class AddStoreDto {
        @NotBlank
        String name;

        @NotBlank
        String specAddress;

        @NotNull
        Long regionId;
    }
}
