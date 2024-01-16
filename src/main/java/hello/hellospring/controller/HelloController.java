package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!!");
        return "hello";  // resources > templates > hello.html을 찾아서 랜더링해라 (이 화면을 실행시켜라)
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody  // http 통신 프로토콜의 응답 body 부분에 "hello" + name 이 데이터를 직접 넣어주겠다는 의미
    public String helloString(@RequestParam("name") String name) {
        // 위와 차이점: View 없이 아래 문자가 그대로 내려감
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        /* 스프링에서는 객체를 반환하고, RespnseBody라고 해두면 그냥 JSON으로 반환하는 게 default */
    }

    static class Hello {
        private String name;

        // 꺼낼 때
        public String getName() {
            return name;
        }

        // 넣을 때
        public void setName(String name) {
            this.name = name;
        }
    }
}
