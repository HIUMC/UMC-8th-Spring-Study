package umc.spring.converter;

<<<<<<< HEAD
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
=======
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
>>>>>>> finger9999
                .region(region)
                .build();
    }

<<<<<<< HEAD
    public static StoreResponseDTO.AddResultDTO toAddResultDTO(Store store) {
        return StoreResponseDTO.AddResultDTO.builder()
                .storeId(store.getId())
=======
    public static StoreResponseDTO.createResultDTO storeToStoreResponseDTO(Store store){
        return StoreResponseDTO.createResultDTO.builder()
                .id(store.getId())
                .name(store.getName())
                .regionName(store.getRegion().getName())
>>>>>>> finger9999
                .createdAt(LocalDateTime.now())
                .build();
    }
}
