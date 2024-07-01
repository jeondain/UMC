package umc.spring.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.study.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
