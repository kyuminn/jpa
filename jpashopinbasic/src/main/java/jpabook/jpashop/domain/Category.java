package jpabook.jpashop.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // 셀프 상-하위 양방향 연관관계 가능
    // 상위 카테고리 한개에 하위 카테고리 여러개 있을 수 있음.
    @ManyToOne
    @JoinColumn(name="PARENT_ID")
    private Category parent; // 상위 카테고리

    @OneToMany(mappedBy = "parent") // 하위 카테고리
    private List<Category> child = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="CATEGORY_ITEM",joinColumns = @JoinColumn(name = "CATEGORY_ID"),
    inverseJoinColumns = @JoinColumn(name="ITEM_ID"))
    private List<Item> items = new ArrayList<>();

}
