package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
// 실제 스프링이 해주는 부분
    public static void main(String[] args) {
        //persistence.xml에서 정한 유닛네임을 적어줌("hello")
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction(); // jpa에서 데이터를 변경하는 모든 작업은 트랜잭션 안에서 실행되어야 한다.
        tx.begin();
        // emf-em : connection pool과 connection 객체의 느낌인듯..?

        try {
//            비영속
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");
//            영속 (EntityManager안에 있는 영속성 컨텍스트 안에서 이 member가 관리된다는 의미)
//            em.persist(member);
//            -- Read member--
            Member member2 = em.find(Member.class, 1L);
            member2.setName("HelloJPA");
            // 수정 시 em.persist 해서 저장하지 않아도 됨. 마치 java collection을 다루는 것처럼 set~만 해서 하면 자동으로
            // 데이터베이스에 반영된다.
            // why? JPA를 통해서 가져온 데이터는 JPA에서 계속 dirty check를 한다 (변경감지 기능.)
            // 트랜잭션 커밋 시점 직전에 기존 데이터와 값이 다른 것이 있으면 update 쿼리를 날린 뒤에 커밋함 (신가하다!)
//            -- Delete member--
//            em.remove(member2);


            // -- jpql 맛보기 --
            // jpql : database table을 대상으로가 아닌 , Member 객체를 대상으로 쿼리를 짬
            //List<Member> resultList = em.createQuery("select m from Member as m", Member.class).getResultList();'
            List<Member> resultList= em.createQuery("select m from Member as m where m.id>=1",Member.class).getResultList();

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close(); //was가 내려갈때 entitymanagerfactory 닫아줘야 한다.

    }
}
