package umc.spring.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.service.RegionService.RegionService;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class StoreConverter {

    private final RegionService regionService;

    public Store toStore(StoreRequestDTO.createDto request){
        Region region = regionService.regionById(request.getRegionId());

        return Store.builder()
                .name(request.getName())
                .region(region)
                .build();
    }

    public static StoreResponseDTO.createResultDTO storeToStoreResponseDTO(Store store){
        return StoreResponseDTO.createResultDTO.builder()
                .id(store.getId())
                .name(store.getName())
                .regionName(store.getRegion().getName())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
