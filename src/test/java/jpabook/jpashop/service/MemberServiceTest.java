package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)    //Junit 실행 시 Spring 과 함께 실행하겠다.
@SpringBootTest                 //SpringBootContainer 안에서 테스트를 돌리겠다.
@Transactional                  //Test 케이스의 Transaction 은 실행 후 Rollback 을 시킨다.
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(saveId));
    }
    
    @Test(expected = IllegalStateException.class)   //try catch 없이 return 값이 해당 예외일 경우 true 이다
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("lee");

        Member member2 = new Member();
        member2.setName("lee");

        //when
        memberService.join(member1);
        memberService.join(member2);//예외가 발생해야 한다.
        //then
        fail("예외가 발생해야 한다.");
    }

}