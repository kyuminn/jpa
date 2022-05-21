package hellojpa;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn // 단일 테이블 전략은 이 어노테이션 없어도 default로 들어감. DTYPE은 운영상 있는 것이 좋음.
public abstract class Item {
    // TABLE_PER_CLASS 인 경우에는 추상 클래스로 만들어야함. -> 이 전략의 경우 부모 클래스 타입으로 SELECT 문을 (FIND) 날릴 때
    // UNION으로 모든 테이블을 다 찾아보니까 성능상으로 단점이 생길 수 있음.

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
