package umc.spring.service.StoreService;

import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {

    Store createStore(StoreRequestDTO.createDto request);
}
