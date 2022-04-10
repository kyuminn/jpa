package jpabook.jpashop.service;


import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jpabook.jpashop.domain.Member;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /*
    주문
    */
    @Transactional
    public Long order(Long memberId, Long itemId,int count){

        // 주문 시 필요한 멤버, 아이템 엔티티 조회회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문 상품 생성
        OrderItem orderItem=OrderItem.createOrderItem(item,item.getPrice(),count);
        // cf ) Order class의 cascade 옵션 덕분에 OrderItem과 Delivery는 자동으로 persist 된다


        // 주문 생성
        Order order = Order.createOrder(member,delivery,orderItem);

        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    /*
    주문 취소
    */

    @Transactional
    public void cancelOrder(Long orderId){
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();

        /*
         jpa의 강점... cancel후 update 쿼리문을 또 날리는게 아니라
         로직 안에서 entity만 바꿔주면
         변경내역 감지가 일어남.
         */
    }


    // 검색
//    public List<Order> findOrders(OrderSearch orderSearch){
//        return orderRepository.findAll(orderSearch);
//    }
}