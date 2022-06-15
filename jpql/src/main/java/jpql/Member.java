package jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NamedQuery(
        name = "Member.findByUsername",
        query = "select m from Member as m where m.username=:username"
)
// NamedQuery : 쿼리를 미리 정해놓고 재활용해서 쓰는 느낌 . 어플리케이션 로딩 시점에 jpql에서 sql로 번역된 이후에 캐싱해서 가져오기 때문에 장점이 있음. (spring data jpa에서는 @query)
// 실행 시점에서 컴파일 에러가 뜨기 때문에 잘못 수정되었다 하더라도 안전.
// 참고로 NamedQuery를 xml에 정의할 수도 있음. (xml이 우선권을 가짐.)

public class Member {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    // 연관관계 편의 메서드 ( 두 클래스 중 하나에만 해주면 됨,,)
    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }

    // toString override 할 때 team 조심
    // 양방향 toString 시 무한루프 타기 때문에..
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
