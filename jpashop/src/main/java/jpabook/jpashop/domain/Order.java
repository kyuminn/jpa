package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//엔티티가 비즈니스 로직을 가지고 있어서 객체 지향의 특성을 적극 활용하는 것을 domain model pattern 이라고 함. <===> transaction script pattern.
// jpa와 같은 orm을 사용할 때는 domain model pattern을 주로 사용한다.

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id") //fk name 이 member_id
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    // cascade 전파 전략 사용 시 order만을 persist 했을 때 Collection에 있는 orderItems도 모두 자동으로 persist 해준다!
    private List<OrderItem> orderItems = new ArrayList<>();


    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL) // order 저장시 delivery도 같이 persist 해준다 (persist)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status ; // 주문상태[ORDER,CANCLE]


    // 모든 entity는 persist를 하고 싶으면 기본적으로는 각자 persist 를 따로 해줘야 하는데
    // cascade 를 지정해주면 따로 하지 않아도 된다.



    //== 연관관계 편의 메서드==> 양방향 바인딩이 목적!!//
    public void setMember(Member member){
        this.member=member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery=delivery;
        delivery.setOrder(this);
    }
    
    //==생성 메서드==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem: orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    // ==비즈니스 로직==//
    // 주문 취소

    public void cancel(){
        if(delivery.getDeliveryStatus()==DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다");
        }
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem:this.orderItems){ //this를 꼭 써야한다기보다는..?
            orderItem.cancel();
        }
    }


    //== 조회 로직==//

    //전체 주문 가격 조회
    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        // lamda
        // totalPrice += orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
        return totalPrice;
    }

}