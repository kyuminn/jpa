package jpabook.jpashop.repository;

import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

// JPA에서 동적 쿼리를 어떻게 해결할 것인가?
@Getter
@Setter
public class OrderSearch {

    private String memberName; // 회원 이름
    private OrderStatus orderStatus; // 주문 상태[ORDER,CANCEL]
}
