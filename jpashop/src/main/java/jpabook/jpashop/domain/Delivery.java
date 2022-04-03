package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY) // mappedBy="" 안에는 Order class의 private Delivery "delivery" 가 옴
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // default가 ORDINAL(0,1,2) 이므로 조심하기 ! 중간에 다른 인자를 추가했을 때 순서 밀릴수 있으니 왠만하면 STRING 쓰기 !
    private DeliveryStatus deliveryStatus; // READY, COMP


}
