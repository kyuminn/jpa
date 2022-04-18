package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Service class 에서 따로 처리할 부분이 없고, 단순히 Repository로 위임하는 역할만 가지고 있을 때,
// 굳이 Service 단이 있어야 하나 ? => 없어도 될 듯..
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    // Spring transaction에 의해 commit 이 되면 jpa 가 변경 감지.
    // flush = 영속성 엔티티 중에 변경된 부분이 있는지 찾음.
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity ){
        Item findItem= itemRepository.findOne(itemId); // 영속성 엔티티를 가져옴
        //findItem.change(name,price,stockQuantity); //setter 없이 새로 아예 method를 파는 것이 훨씬 더 나은 방식임.
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
        // 이미 영속성 관리 대상인 엔티티를 가져와서 수정했으므로
        //itemRepository.save(findItem); 할 필요가 없음.
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
