package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderData; //주문시간

    @Enumerated(EnumType.STRING) //EnumType 은 ORDINAL STRING 이 있는데 ORDINAL 은 상태가 1,2 이렇게 숫자로 들어가며 다른 상태발생시 XXX및 다른상태로 값이 들어간다.
    private OrderStatus status; //주문상태 [ORDER, CANCEL]

}//class
