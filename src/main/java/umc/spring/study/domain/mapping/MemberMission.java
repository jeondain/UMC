package umc.spring.study.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import umc.spring.study.domain.FoodCategory;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.common.BaseEntity;
import umc.spring.study.domain.enums.MissionStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("CHALLENGING")
    private MissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private FoodCategory foodCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    public void setMember(Member member){
        this.member = member;
        if (!member.getMemberMissionList().contains(this)) {
            member.getMemberMissionList().add(this);
        }
    }

    public void setMemberMission(Mission mission){
        this.mission = mission;
    }
}