package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member extends BaseEntity{

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MEMBER_ID")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    // 필요할 때만 아래 양방향 관계 추가해주면 됨. (필요 없을 시엔 안해도 됨)
    // 쌤은 이 부분 필요없어 보인다고 말하심.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
