package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.dto.MemberDto;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm()); //validation...! 을 위한 빈 껍데기 주입
        return "members/createMemberForm";
    }
     /* @NotBlank와 같은 어노테이션 방식 유효성 검사를 하려면 command 객체 앞에 @Valid 어노테이션을 붙여야 함
     @Valid 한 커맨드 객체 뒤에 BindingResult 객체가 있으면 이 객체 안에 오류가 담겨서 return되는 화면까지
     error message를 가져가서 뿌릴 수 있게 해줌! (dependency에서 spring-thymeleaf )
      */
    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult bindingResult){
        // MemberForm form 앞에 @ModelAttribute 생략 가능.
        if(bindingResult.hasErrors()){
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(),form.getStreet(),form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";

    }
    
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        // 실무에서는 이 부분 members 로 그대로 가져오지 말고 dto로 변환해서 뿌리는 것이 맞음.
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        MemberDto members2=modelMapper.map(members, MemberDto.class);
//        model.addAttribute("members2",members2);
        return "members/memberList";
    }
}
