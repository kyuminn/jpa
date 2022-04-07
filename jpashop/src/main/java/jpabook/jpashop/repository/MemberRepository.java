package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

// @Service, @Repository, @Controller = > Spring에서 component-scan에 자동으로 include 되어 객체를 생성해준다!
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //Spring 이(boot가?) jpa entitymanager를 1,생성해서 2.inject까지 해준다.
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    };

    public Member findOne(Long id){
        return em.find(Member.class, id);
    };

    // jpql 사용!
    // jpql => query문의 사용 대상이 table이 아니라 entity라고 생각하면 됨!
    public List<Member> findAll(){
      return em.createQuery("select m from Member m",Member.class).getResultList();
    };

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name=:name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }

}
