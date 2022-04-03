package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name="category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name="category_item",joinColumns = @JoinColumn(name="category_id"),inverseJoinColumns = @JoinColumn(name="item_id")) //다대다 관계에서는 1:다 다:1 로 풀어내주는 중간 테이블이 필요함.
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Category parent;


    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    // x to ONE (ONETOONE,MANYTOONE)은 fetch strategy default가 eager 이기 때문에 직접 lazy로 설정해줘야 함!
    // x to Many는 기본이 lazy로 설정되어있기 때문에 따로 설정해줄 필요 없음.!

    // == 연관관계 메서드 ==/
    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }
}
