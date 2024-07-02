package umc.spring.study.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.apiPayload.code.status.ErrorStatus;
import umc.spring.study.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.study.apiPayload.exception.handler.MemberMissionHandler;
import umc.spring.study.converter.MemberConverter;
import umc.spring.study.converter.MemberMissionConverter;
import umc.spring.study.converter.MemberPreferConverter;
import umc.spring.study.domain.FoodCategory;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.domain.mapping.MemberPrefer;
import umc.spring.study.repository.FoodCategoryRepository;
import umc.spring.study.repository.MemberRepository;
import umc.spring.study.repository.MissionRepository;
import umc.spring.study.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);

        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);
        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});


        List<Mission> missionList = request.getMemberMission().stream()
                .map(missionId -> missionRepository.findById(missionId)
                        .orElseThrow(() -> new MemberMissionHandler(ErrorStatus.MISSION_NOT_FOUND)))
                .collect(Collectors.toList());

        List<MemberMission> memberMissionList = MemberMissionConverter.toMemberMissionList(missionList);
        memberMissionList.forEach(memberMission -> memberMission.setMember(newMember));

        return memberRepository.save(newMember);
    }
}