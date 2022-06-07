package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpql");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setUsername("member a");
            member.setAge(10);
            em.persist(member);
            
            
            // 반환값이 명확할 때 TypeQuery 사용
            // 반환 타입이 명확하지 않을 때 Query 사용
            TypedQuery<Member> query1 = em.createQuery("select m from Member as m", Member.class);

            // getResultList();는 결과가 없으면 빈 리스트를 반환하기 때문에 NPE를 고민하지 않아도 됨.
            // 왠만하면 getSingleResult() 사용하지 말 것 (반드시 결과가 하나가 있어야 함 아니면 exception 터짐)
            List<Member> resultList = query1.getResultList();
            Member singleResult = query1.getSingleResult();

            TypedQuery<String> query2 = em.createQuery("select m.username from Member as m", String.class);
            Query query3 = em.createQuery("select m.username,m.age from Member as m");

//            TypedQuery<Member> query4 = em.createQuery("select m.username from Member as m where m.username= :username",
//                    Member.class);
//            query4.setParameter("username", "member1");
//            Member singleResult1 = query4.getSingleResult();

            List<Member> resultList1 = em.createQuery("select m from Member as m where m.username=:username", Member.class)
                    .setParameter("username", "member1")
                    .getResultList();

            // cf 위치 기반의 parameter bind는 왠만하면 사용하지 말것. 이름 기반 parameter binding 쓰시옹.

            tx.commit();

        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
