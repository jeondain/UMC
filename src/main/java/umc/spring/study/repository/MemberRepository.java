package umc.spring.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.study.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
