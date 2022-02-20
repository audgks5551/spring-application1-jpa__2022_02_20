package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;
//    @Autowired EntityManager em;

    @Test
    @Rollback(false)
    public void 회원가입()  throws Exception {
        // given
        Member member = new Member();
        member.setName("lee");

        // when
        Long savedId = memberService.join(member);

        // then
//        em.flush();
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("lee");

        Member member2 = new Member();
        member2.setName("lee");

        // when
        memberService.join(member1);

        // then
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> memberService.join(member2));
        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());
    }

}