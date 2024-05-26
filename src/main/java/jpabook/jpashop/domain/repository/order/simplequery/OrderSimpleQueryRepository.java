package jpabook.jpashop.domain.repository.order.simplequery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {
    private final EntityManager em;

    public List<OrderSimpleQueryDto> findOrderQueryDtos(){
        List<OrderSimpleQueryDto> result = findOrderDtos(); //query 1번
        //컬렉션은 dto로 한번에 못가져오니 그냥 가져온 뒤 컬렉션은 따로 돌림. 그만큼 query 발생 결국 n+1문제가 있다.
        result.forEach(o -> {
            List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());
            o.setOrderItems(orderItems);
        });
        return result;
    }

    private List<OrderItemQueryDto> findOrderItems(long orderId) {
        return em.createQuery(
                "select new jpabook.jpashop.domain.repository.order.simplequery.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count) " +
                        " from OrderItem oi" +
                        " join oi.item i" +
                        " where oi.order.id = :orderId", OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery("select new jpabook.jpashop.domain.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address) from Order o" +
                        " join o.member m" +
                        " join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }
}
