package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    /*
    @GetMapping은 HTTP GET 요청이 해당 URL ("/members/new")로 들어왔을 때, 이를 처리하는 메소드
     */
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 보통 데이터를 등록할 때는 post, 조회할 때는 get
    // url은 위와 똑같지만 post 방식인 아래의 메소드가 선택이 됨.
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }
}
