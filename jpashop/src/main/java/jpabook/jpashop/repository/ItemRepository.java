package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    // 상품 저장
    public void save(Item item){
        if(item.getId()==null){
            em.persist(item); //신규 등록
        }else{
            em.merge(item); // 이미 db에 등록된 것을 update하는 느낌?
            // jpa에서 권장하는 entity 수정 방식은 merge 가 아닌 '변경 감지'
        }
    }

    // 단건 조회
    public Item findOne(Long id){
        return em.find(Item.class,id);
    }

    // 여러건 조회
    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

}
