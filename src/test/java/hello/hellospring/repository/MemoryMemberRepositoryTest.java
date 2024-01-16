package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트가 끝날 때마다 리포지토리를 깔끔하게 지워주는 코드
    @AfterEach  // @AfterEach _ 어떤 메소드가 끝날 때마다 호출이 됨.
    public void afterEach() {
        repository.clearStore();
    }

    // save()가 잘 동작하는지 확인
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");  // 회원 이름 세팅

        repository.save(member);  // repository에 회원 저장, 저장할 때 자동으로 Id 세팅됨(save()함수 내에서)

        Member result = repository.findById(member.getId()).get();  // Id로 값을 꺼내고

        // (검증) member를 생성해서 회원 이름을 세팅한 것과 꺼낸 것과 똑같으면 참(true)/성공
        // System.out.println("result = " + (result == member));  // 방법 1
        // 방법 2) 출력되는 것은 없지만 다르면 에러가 뜸, (기대값, 실제값) 비교
        // Assertions.assertEquals(member, result);
        // 방법 3)
        assertThat(member).isEqualTo(result);
    }

    //findByName()이 잘 동작하는지 확인
    @Test
    public void findByName() {
        // spring1(member1) 회원가입
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // spring2(member2) 회원가입
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        // spring1 -> spring2로 변경하면 에러 -> 다른 객체다

        assertThat(result).isEqualTo(member1);
    }

    // findAll()이 잘 동작하는지 확인
    @Test
    public void findAll() {
        // spring1(member1) 회원가입
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // spring1(member1) 회원가입
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
