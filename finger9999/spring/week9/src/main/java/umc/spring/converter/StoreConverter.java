package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.*;
import umc.spring.web.dto.Store.StoreResponseDTO;
import umc.spring.web.dto.Store.StoreRequestDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static Store toStore(StoreRequestDTO.AddStoreDto request, Region region) {
        return Store.builder()
                .name(request.getName())
                .address(request.getSpecAddress())
                .region(region)
                .build();
    }

    public static StoreResponseDTO.AddStoreResultDTO toAddStoreResultDTO(Store store) {
        return StoreResponseDTO.AddStoreResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review) {
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt() != null ? review.getCreatedAt().toLocalDate() : null)
                .body(review.getBody())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList) {
        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static StoreResponseDTO.MissionPreViewDTO missionPreViewDTO(Mission mission) {
        return StoreResponseDTO.MissionPreViewDTO.builder()
                .missionId(mission.getId())
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .createdAt(mission.getCreatedAt() != null ? mission.getCreatedAt().toLocalDate() : null)
                .build();
    }

    public static StoreResponseDTO.MissionPreViewListDTO missionPreViewListDTO(Page<Mission> missionList) {
        List<StoreResponseDTO.MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(StoreConverter::missionPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.MissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }
}
