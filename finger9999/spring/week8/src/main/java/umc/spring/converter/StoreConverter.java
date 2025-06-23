package umc.spring.converter;

import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.web.dto.Store.StoreResponseDTO;
import umc.spring.web.dto.Store.StoreRequestDTO;

import java.time.LocalDateTime;

public class StoreConverter {

    public static Store toStore(StoreRequestDTO.AddDto request, Region region) {
        return Store.builder()
                .name(request.getName())
                .address(request.getSpecAddress())
                .region(region)
                .build();
    }

    public static StoreResponseDTO.AddResultDTO toAddResultDTO(Store store) {
        return StoreResponseDTO.AddResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
