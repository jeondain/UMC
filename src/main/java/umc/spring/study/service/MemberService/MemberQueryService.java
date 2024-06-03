package umc.spring.study.service.MemberService;

import umc.spring.study.domain.Member;

import java.util.Optional;

public interface MemberQueryService {

    Optional<Member> findMember(Long id);

}
