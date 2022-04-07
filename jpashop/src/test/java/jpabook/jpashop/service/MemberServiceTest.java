package jpabook.jpashop.service;

import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import jpabook.jpashop.domain.Member;
import static org.junit.Assert.*;

// settings > live template에서 단축키 설정 가능!
// settings > editor> live template 에서 줄임말 자동완성 설정 가능!!
//test case 에 @transactional annotation이 있으면
//test 가 끝나면 rollback 해버림.
//Rollback(false)를 걸어주면 rollback안함!
//ctrl+alt+v => method결과 반환 데이터 타입을 자동으로 설정해주는 단축키!
@RunWith(SpringRunner.class) // junit 실행 시 spring이랑 같이 엮어서 실행할래!
@SpringBootTest  // Spring Boot를 띄운 상태에서(Spirng container안에서) test를 실행하겠다는 의미 (이거 없으면 아래 @Autowired 모두 실패.)
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    // 회원가입이 잘 되는지 테스트
    @Test
    //@Rollback(false)
    public void regist() throws Exception{
        //given -주어졌을때
        Member member = new Member();
        member.setName("kim");
        //when -이거 실행하면
        Long savedId = memberService.join(member);

        //then -결과가 이렇게 나와야함
        assertEquals(member, memberRepository.findOne(savedId));
    }

    // 동일한 이름이 있을 때 예외 발생
    //발생 가능한 예외
    @Test (expected = IllegalStateException.class)
    public void dupliName () throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2= new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2); // 예외가 발생해야 함.


        //then
        // 여기까지 오면 안될 때 fail 사용
        // 위에서 예외가 터져야함.
        fail("예외가 발생해야 한다.");
    }
}