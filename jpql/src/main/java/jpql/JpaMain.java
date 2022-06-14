package jpql;

import javax.persistence.*;
import java.util.Collection;
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


//            em.flush();
//            em.clear();

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

            // fetch join - 실무에서 많이 쓰임 ( fetchType.Eager과 같은 한방 쿼리,)
            // 상황가정 - team에 속한 member를 모두 가져오고 싶을 때
            Team teamA = new Team();
            teamA.setName("팀 A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀 B");
            em.persist(teamB);

            Member member1= new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

//            String query = "select m from Member as m ";
//            List<Member> resultList = em.createQuery(query, Member.class).getResultList();
//
//            for (Member member : resultList) {
//                System.out.println("Member : "+ member.getUsername()+member.getTeam().getName()); // getTeam()할때 proxy 객체가 담김
//                // 회원1 : 팀A (SQL)
//                // 회원2 : 팀A (1차캐시)
//                // 회원3 : 팀B (SQL)
//
//                // 회원100 명 -> 1 + N (1 : 첫번째 member 가져오는 경우.. N : 그 멤버에서 팀을 가져올 때부수적인 쿼리) 즉시/지연 로딩 모두 발생함. -> fetch join으로 풀어야함
//            }
            // 지연로딩으로 setting해도 fetch join이 우선순위임.
//            String query = "select m from Member as m join fetch m.team"; // 이때는 join문으로 select쿼리가 한개만 나감. getTeam()한 것은 proxy가 아니라 실제 entity임.
//            // join문으로 가져오기 때문에 영속성context에 이미 team이 담겨있기 때문!
//            List<Member> resultList = em.createQuery(query, Member.class).getResultList();
//            for (Member member : resultList) {
//                System.out.println("member:"+member.getUsername()+member.getTeam().getName());
//            }

            //  jpql distinct : 1. query 상에 distinct 추가 , 2. entity 상의 중복 제거! (이 경우에는 같은 식별자를 가진 Team entity 제거)
            // distinct가 없는 경우 team을 기준으로 member를 조인한 경우 한 팀에대해 여러 멤버가 존재할 수 있기 때문에 (join하면서 data가 뻥튀기 되는 경우임)
            // 여러 멤버가 속해있는 팀을 기준으로 쿼리 결과가 반환되어, 중복된 데이터가 들어갈 수 있다 -> 그 경우에는 distinct 키워드를 사용하면 된다.
            // distinct 키워드 유/무 경우에 result값 달라지는 것 확인.
            // fetch join 대상에는 별칭을 줄 수 없다. (hibernate는 사용 가능하지만, 가급적 사용하지 말 것.)
            // jpa에서 team 에서 member를 가져올 때는 모든 member를 가져오도록 설계되어 있기 때문에 조건절로 특정 팀에서 특정 member만 가져오면
            // 안됨
            // 만약 특정 팀에서 특정 멤버 집합만 가져오고 싶으면 fetch join 별칭으로 해결 할 것이 아니라 별도의 쿼리를 짜서 수행해야 함.

            // fetch join 시 일대일, 다대일 -> 데이터 뻥튀기 안됨 , 일대다 -> 데이터 뻥튀기 됨
            String query1 = "select distinct t from Team as t join fetch t.members";
            List<Team> resultList1 = em.createQuery(query1, Team.class).getResultList();
            for (Team team : resultList1) {
                System.out.println(team.getName()+"|"+team.getMembers().size());
            }

            // 그냥 join : select t from Team t join t.members m
                // select문에 team만 가져옴 ( join은 하되 데이터는 team만!)
            // fetch join : select t from Team t join fetch t.members => fetch join을 사용할 때는 사실상 즉시 로딩이 일어난다고 보면 됨.
                // select문에서 members도 가져옴

            // 정리 = 실무에서 로딩 전략은 모두 지연 로딩을 사용하되, 최적화가 필요한 곳에서 fetch join을 적용한다
            // 실무에서 성능문제의 70~80%는 1+N 문제이므로 , fetch join을 사용하도록 한다 (지연 로딩도 1+N 문제가 발생)

            // 페치 조인은 그래프를 유지할 때 사용하면 효과적 ( member.team...)
            // 여러 테이블을 조인해서 엔티티가 가진 모양이 아닌 전혀 다른 결과를 내야 하면, 페치 조인 말고 일반 조인을
            // 사용하여 필요한 데이터들만 dto로 반환하는 것이 효과적.
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
