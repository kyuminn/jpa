package jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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
