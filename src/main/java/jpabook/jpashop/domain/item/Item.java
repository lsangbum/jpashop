package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //한테이블에 다 넣어 사용하는 SINGLE_TABLE 을 사용
@DiscriminatorColumn(name = "dtype") // 데이터 저장시 dtype이라는 컬럼에 각 객체를 구분지어 저장한다. 각객체에는 @DiscriminatorValue 으로 구분 값이 저장되어있다.
@Getter
@Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

}//class
