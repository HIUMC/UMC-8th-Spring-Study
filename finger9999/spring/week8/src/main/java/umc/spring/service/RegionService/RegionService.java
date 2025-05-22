package umc.spring.service.RegionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.domain.Region;
import umc.spring.repository.RegionRepository.RegionRepository;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;

    // 에러 로직 추가 예정
    public Region regionById(Long id) {
        return regionRepository.findById(id).orElseThrow();
    }
}
