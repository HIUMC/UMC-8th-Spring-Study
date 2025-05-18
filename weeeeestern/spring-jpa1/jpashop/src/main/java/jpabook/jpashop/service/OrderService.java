package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    // 서비스는 엔티티에 필요한 요청만..
    // 엔티티에 비즈니스 로직이 있고, 객체 지향의 특성을 적극 활용하는 것은?
    // 도메인 모델 패턴
    // <-> 트랜잭션 스크립트 패턴
    //주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 설정
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문 상품 생성
        OrderItem orderItem =  OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order =  Order.createOrder(member, delivery, orderItem);

        // 저장
        orderRepository.save(order);
        // -> cascade 처리가 되어 있으므로, order와 delivery, orderItem 한 번에 DB에 insert 된다.
        return order.getId();
    }

    //취소
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
        // JPA 를 사용하지 않으면, service 에서 sql문부터 비즈니스 로직을 다 쓸 수 밖에 없다.
        // JPA를 사용하면 엔티티의 데이터들만 바꾸고, Dirty checking 을 통해 DB에 변경사항 쿼리문
    }

    //검색
//    public List<Order> findOrders(OrderSearch orderSearch){
//        return
//    }
}
