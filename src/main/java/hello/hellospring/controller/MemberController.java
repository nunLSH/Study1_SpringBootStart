package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/* 이 notation을 달아두면 스프링이 뜰 때,
    MemberController 객체를 생성해서 스프링에 넣어둠 -> 그리고 스프링이 관리
    : '스프링 컨테이너에서 스프링 빈이 관리된다' 고 표현 */
@Controller
public class MemberController {

    private final MemberService memberService;

    /* 생성자에 @Autowired
       : MemberController가 생성될 때, 스프링 컨테이너에서 스프링 빈에 등록되어있는 memberService 객체를 찾아서 넣어줌. */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
