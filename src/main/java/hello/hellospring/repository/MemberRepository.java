package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // 기능 정의

    // 1) 회원을 저장하면 저장된 회원 반환
    Member save(Member member);
    // null 값을 고려하여 Optional 사용
    // 2,3) id/name으로 회원 찾기
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    // 4) 지금까지 저장된 모든 회원 list를 반환
    List<Member> findAll();
}
