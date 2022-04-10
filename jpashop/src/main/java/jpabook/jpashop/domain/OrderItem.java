package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name="order_item")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량

    //기본 생성자 쓰지 말라는 의미로 보통 사용. (암묵적 의미)
    /* createOrderItem으로 생성할 때 필요한 값 넣도록 설계하였기 때문에
    newOrderItem() 후 setter method로 값 설정하는 거 막기 위해서
    기본 생성자를 protected로 막음 (보통 Order class를 상속해서 하는 경우가 없기 때문에)
    // 이것도 롬복으로 설정할 수 있음.
     */
//    protected OrderItem(){
//
//    }

    //==생성 메서드 ==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        // 주문시 재고 빼기
        item.removeStock(count);
        return orderItem;
    }

    // ==비즈니스 로직==//
    public void cancel() {
        getItem().addStock(count);
    }

    //== 조회 로직==//
    
    //주문상품 전체 가격 조회
    public int getTotalPrice() {
        return getOrderPrice()*getCount();
    }
}
