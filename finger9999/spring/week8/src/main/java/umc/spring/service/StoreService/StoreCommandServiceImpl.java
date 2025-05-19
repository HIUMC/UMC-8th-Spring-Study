package umc.spring.service.StoreService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreConverter storeConverter;

    @Override
    public Store createStore(StoreRequestDTO.createDto request) {
        Store newStore = storeConverter.toStore(request);

    }
}
