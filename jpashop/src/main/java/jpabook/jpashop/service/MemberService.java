package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor   // final이 붙은 field만 가지고 생성자를 만들어준다.
//JPA의 데이터 변경이나 로직은 모두 transaction안에서 실행되어야 한다.
@Transactional(readOnly = true) // 기본적인 설정. readOnly가 true이면 resource를 좀 덜 씀.
public class MemberService {
    // create test 단축키 :command+shift+T =>create new TEST
    // 한번 주입되고 나서는 따로 이 객체에 변경사항이 없기 때문에 final로 선언해주는 것이 좋음.
    private final MemberRepository memberRepository;

    /*
     - field injection => test code 작성시 까다로움.
     @Autowired
     private MemberRepository memberRepository;

     - setter injection => 어플리케이션 실행 도중 memberRepository가 바뀔 수 있음
     @Autowired
     public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository ;
        };

     -> 요즘은 생성자 injection을 많이 쓴다!
     => 생성 시에 조립이 끝나기 때문에 중간에 set으로 바꿀 수가 없음 (장점!)
     => 테스트 작성시에도 좋음 (의존관계가 명확해서 놓치지 않을 수도 있음)
     @Autowired
     public MemberService(MemberRepository memberRepository){
       this.memberRepository = memberRepository;
       }
    */

    // 회원 가입
    @Transactional //기본적인 설정보다 우선순위 높음. 기본 readOnly=false;
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
      // EXCEPTION
      List<Member> findMembers=memberRepository.findByName(member.getName());
      if(!findMembers.isEmpty()){
          throw new IllegalStateException("이미 존재하는 회원입니다");
      }
    }

    // 회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 회원 단건 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
