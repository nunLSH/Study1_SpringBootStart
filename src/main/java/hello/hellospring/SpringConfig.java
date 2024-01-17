package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean // 스프링 빈을 등록할 거라는 의미
    public MemberService memberService() { // memberService가 스프링 빈에 등록됨
        return new MemberService(memberRepository());
        // 스프링 빈에 등록된 memberRepository를 MemberService에 넣어줌
    }

    @Bean
    public MemberRepository memberRepository() { // memberRepository가 스프링 빈에 등록됨
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
