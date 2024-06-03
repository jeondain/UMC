package umc.spring.study.service.StoreService;

import umc.spring.study.domain.Region;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.Store;
import umc.spring.study.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Store addStore(StoreRequestDTO.AddStoreDto request, Region region);
    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReveiwDto request);
}
