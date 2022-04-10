package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Getter
@Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories=new ArrayList<>();

    // 비즈니스 로직

    // itemService 같은 곳에서 stock을 get 해서 더하고 빼서 set 하기보다는,
    // 좀더 객체지향 스럽게 데이터를 가지고 있는 곳에 비즈니스 메서드를 넣는것이 가장 좋음. => 높은 응집력 (getter, setter 쓰기보다는..)

    // 재고수량 증가 로직
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    // 재고 감소
    public void removeStock(int quantity){
        int restStock = this.stockQuantity-quantity;
        if(restStock<0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
