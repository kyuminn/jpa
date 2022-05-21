package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ORDERS")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue // default strategy auto이므로 생략.
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID") //외래키로 member_id 설정됨. 외래키 있는 부분이 연관관계의 주인.
    private Member member;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //필요한 경우에만 아래 양방향 연관관계 추가 (개인 프로젝트나,,,실무가 아닌 경우에는 거의 단방향만 제대로 해도 상관 x)
    // 실전에서는 jpql을 많이 이용하기 때문에 양방향 연관관계를 추가하는 경우가 많음.
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="DELIVERY_ID")
    private Delivery delivery;

    // 앙뱡향 연관관계 편의 메서드는 둘중 한 클래스에만 넣어두면 된다. 어디에 넣을건지는 그때그때 상황마다 다름.
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);

    }
}
