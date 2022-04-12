package jpabook.jpashop.repository;


import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class,id);
    }

    // 검색기능
// mybatis는 동적 쿼리 되게 편하다는 장점이 있었음.. jpa는? querydsl로 처리.

//    public List<Order> findAll(OrderSearch orderSearch){


//        return;
//    }
}
