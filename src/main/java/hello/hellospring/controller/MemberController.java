package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 스프링 빈을 등록하는 2가지 방법
 * 1. 컴포넌트 스캔 과 의존관계 설정  @Component , 싱글톤 등록(하나만 등록, 공유)
 * 2. 자바코드로 직접 빈 등록하기
 * @Configuration , @Bean
 *
 *
 * 장단점이 있어
 * 생성자 주입을 권장한다.
 * 내가 했던건 필드 주입인데 권장하지 않는다.
 * 
 * AOP가 필요한 상황
 * 모든 메소드의 호출 시간을 측정하고 싶다면
 * 공통 관심 사항 vs 핵심 관심 사항
 * 회원가입 시간, 회원 조회 시간을 측정하고 싶다면?
 *
 *
 *
 */
@Controller
public class MemberController {

    /**
     * 스프링 컨테이너에서 받아서 쓰도록 해야해
     */
    private final MemberService memberService;

    //생성자 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }





}
