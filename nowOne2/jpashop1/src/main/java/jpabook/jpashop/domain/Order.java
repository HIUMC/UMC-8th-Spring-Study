package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")    //order 테이블을 member 테이블의 주인으로...
    private Member member;

    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //연관관계 메소드//
    public void setMember(Member member){ //한명의 member에만 속하므로 this.member 이렇게 사용
        this.member=member;  //order의 member 필드에 member를 설정하고
        member.getOrders().add(this); //Member class에 orders들을 리스트로 가지고 있고 거기에 새로운 order 객체를 추가
    }

    public void addOrderItem(OrderItem orderItem){ //여러개의 orderItem이 존재할 수 있으므로 orderItems collection에 넣어줌
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this); //1대1 관계이므로 그냥 setter 이용한 것
    }

}
