package umc.spring.study.converter;

import umc.spring.study.domain.Mission;
import umc.spring.study.domain.mapping.MemberMission;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {

    public static List<MemberMission> toMemberMissionList(List<Mission> memberMissionList){

        return memberMissionList.stream()
                .map(mission ->
                        MemberMission.builder()
                                .mission(mission)
                                .build()
                ).collect(Collectors.toList());
    }
}
