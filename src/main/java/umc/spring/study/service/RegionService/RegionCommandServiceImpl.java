package umc.spring.study.service.RegionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.apiPayload.code.status.ErrorStatus;
import umc.spring.study.apiPayload.exception.handler.StoreHandler;
import umc.spring.study.domain.Region;
import umc.spring.study.repository.RegionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionCommandServiceImpl implements RegionCommandService {

    private final RegionRepository regionRepository;

    @Override
    public Region findById(Long regionId) {
        return regionRepository.findById(regionId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.REGION_NOT_FOUND));
    }
}
