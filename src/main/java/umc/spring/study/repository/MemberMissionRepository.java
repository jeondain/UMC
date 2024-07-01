package umc.spring.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.study.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
}
