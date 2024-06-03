package umc.spring.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.converter.StoreConverter;
import umc.spring.study.domain.Region;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.Store;
import umc.spring.study.repository.MemberRepository;
import umc.spring.study.repository.RegionRepository;
import umc.spring.study.repository.ReviewRepository;
import umc.spring.study.repository.StoreRepository;
import umc.spring.study.web.dto.StoreRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Store addStore(StoreRequestDTO.AddStoreDto request, Region region) {

        Store newStore = StoreConverter.toAddStore(request, region);

        return storeRepository.save(newStore);
    }

    @Override
    public Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReveiwDto request) {

        Review review = StoreConverter.toReview(request);

        review.setMember(memberRepository.findById(memberId).get());
        review.setStore(storeRepository.findById(storeId).get());

        return reviewRepository.save(review);
    }
}