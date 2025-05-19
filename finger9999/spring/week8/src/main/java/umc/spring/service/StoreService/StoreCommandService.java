package umc.spring.service.StoreService;

import umc.spring.domain.Store;
<<<<<<< HEAD
import umc.spring.web.dto.Store.StoreRequestDTO;

public interface StoreCommandService {

    Store addStore(StoreRequestDTO.AddDto request);
=======
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {

    Store createStore(StoreRequestDTO.createDto request);
>>>>>>> finger9999
}
