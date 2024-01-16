package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    // 메모리 DB를 비우기 위해서는 리포지토리를 추가해야 함.
    MemoryMemberRepository memberRepository;

    // MemberService와 같은 리포지토리를 사용하기 위한 코드
    // 각 테스트 실행 전에 호출됨.
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 테스트가 끝날 때마다 리포지토리를 깔끔하게 지워주는 코드
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    // 테스트는 한글로 기입 가능
    @Test
    void 회원가입() {
        // given (주어진 상황)
        Member member = new Member();
        member.setName("spring");

        // when (실행했을 때)
        Long saveId = memberService.join(member);

        // then (결과가 이렇게)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when (validateDuplicateMember 예외 발생)
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        /* () -> memberService.join(member2) 이 로직 실행 시,
                    IllegalStateException.class 이 예외가 발생해야 함 */
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");



        /* try-catch 사용할 수 있지만 좋은 방법은 아님

        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            // 예외가 발생해서 정상적으로 성공한 경우
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }

        */

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}