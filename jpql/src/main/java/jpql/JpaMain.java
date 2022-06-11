package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpql");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member = new Member();
//            member.setUsername("member a");
//            member.setAge(10);
//            em.persist(member);


            // 반환값이 명확할 때 TypeQuery 사용
            // 반환 타입이 명확하지 않을 때 Query 사용
//            TypedQuery<Member> query1 = em.createQuery("select m from Member as m", Member.class);

            // getResultList();는 결과가 없으면 빈 리스트를 반환하기 때문에 NPE를 고민하지 않아도 됨.
            // 왠만하면 getSingleResult() 사용하지 말 것 (반드시 결과가 하나가 있어야 함 아니면 exception 터짐)
//            List<Member> resultList = query1.getResultList();
//            Member singleResult = query1.getSingleResult();
//
//            TypedQuery<String> query2 = em.createQuery("select m.username from Member as m", String.class);
//            Query query3 = em.createQuery("select distinct m.username,m.age from Member as m");

//            TypedQuery<Member> query4 = em.createQuery("select m.username from Member as m where m.username= :username",
//                    Member.class);
//            query4.setParameter("username", "member1");
//            Member singleResult1 = query4.getSingleResult();

//            List<Member> resultList1 = em.createQuery("select m from Member as m where m.username=:username", Member.class)
//                    .setParameter("username", "member1")
//                    .getResultList();

            // cf 위치 기반의 parameter bind는 왠만하면 사용하지 말것. 이름 기반 parameter binding 쓰시옹.

//            List<Object[]> resultList2 = em.createQuery("select m.username, m.age from Member m").getResultList();
//
//            Object[] result = resultList2.get(0);
//            System.out.println(result[0]); //username
//            System.out.println(result[1]); // age

            // 위 보다는 아래의 new 명령어 쓰는게 좋을 듯.
//            List<MemberDto> resultList3 =
//                em.createQuery("select new jpql.MemberDto(m.username, m.age) from Member m",MemberDto.class)
//                        .getResultList();
//
//            MemberDto memberDto = resultList3.get(0);
//            System.out.println(memberDto.getUsername());
//            System.out.println(memberDto.getAge());

            // jpa paging - api로 편리하게 구현할 수 있음

//                for (int i =0; i<100; i++){
//                    Member member = new Member();
//                    member.setUsername("member "+i);
//                    member.setAge(i);
//                    em.persist(member);
//                }
//
//                em.flush();
//                em.clear();
//
//            List<Member> resultList4 = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(0)
//                    .setMaxResults(10)
//                    .getResultList();
//            System.out.println(resultList4.size()); //10
//
//            for (Member member : resultList4) {
//                System.out.println(member);
//            }
            // join

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member");
            member.setAge(10);
            member.setTeam(team);
            member.setMemberType(MemberType.ADMIN);
            em.persist(member);

            em.flush();
            em.clear();

            // inner join 에서 inner는 생략 가능 (보통 생략함)
            // left outer join에서 outer는 생략 가능 (보통 생략함)
//        String query = "select m from Member as m inner join m.team as t"; // member pk와 team pk가 같은것끼리 자동으러 조인하는듯.
//        String query2 = "select m from Member as m left outer join m.team as t";
//        String query3 = "select m from Member as m , Team as t where m.username = t.name"; //아무 연관관계 없는 세타 조인 예시 (cross join)
//        List<Member> resultList5 = em.createQuery(query2, Member.class).getResultList();
//
//        List<Member> resultList6 = em.createQuery(query, Member.class)
//                                    .getResultList();
//        List<Member> resultList7 = em.createQuery(query3,Member.class).getResultList();

            // 회원과 팀을 외부 조인하면서 ,팀 이름이 A인 팀만 조인
//        em.createQuery("select m from Member m left outer join m.team t on t.name='teamA'");

            // 서브 쿼리
            // 나이가 평균보다 많은 회원
//        em.createQuery("select m from Member as m where m.age > (select avg(m2.age) from Member as m2)");

            // enum type 조회
//            em.createQuery("select m from Member m  where m.memberType = :memberType")
//                    .setParameter("memberType", MemberType.ADMIN);

            // CASE
//            List<String> resultList = em.createQuery("select " +
//                    "case when m.age <=10 then '학생요금' " +
//                    "when m.age >=60 then '경로요금' " +
//                    "else '일반요금' end from Member m", String.class).getResultList();
//
//            for (String s : resultList) {
//                System.out.println("s="+s); // 위에서 age 10이라고 했으므로 학생 요금 나옴.
//            }
//            // username이 없으면 '이름 없는 회원' 반환
//            // 쿼리 띄어쓰기 주의
//            List<String> resultList1 = em.createQuery("select coalesce(m.username, '이름 없는 회원') from Member m", String.class).getResultList();
//            for (String s : resultList1) {
//                System.out.println("s= "+s);
//            }
//
//            // 사용자 이름이 '관리자'면 null을 반환하고 나머지는 본인의 이름을 반환
//            List<String> resultList2 = em.createQuery("select nullif(m.username, '관리자') from Member as m", String.class).getResultList();
//            for (String s : resultList2) {
//                System.out.println("s="+s);
//            }

//            String query = "select function('group_concat', m.username) from Member m";
            // inject language : alt + enter

            // 실무에서는 왠만하면 묵시적 조인이 아닌 명시적 조인을 사용해야 함.
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
