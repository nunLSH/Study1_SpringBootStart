package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean // 스프링 빈을 등록할 거라는 의미
    public MemberService memberService() { // memberService가 스프링 빈에 등록됨
        return new MemberService(memberRepository());
        // 스프링 빈에 등록된 memberRepository를 MemberService에 넣어줌
    }

    @Bean
    public MemberRepository memberRepository() { // memberRepository가 스프링 빈에 등록됨
        return new MemoryMemberRepository();
    }
}
