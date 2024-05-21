package jpabook.jpashop.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 * xToOne(ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@Controller
@RequiredArgsConstructor
public class OrderSimpleApiController {
}
