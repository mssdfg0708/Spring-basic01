package hello.core.member;

import hello.core.discount.FixDiscountPolicy;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {

    @Test
    void createOrder() {
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "memberA", Grade.VIP));

        OrderService orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order orderA = orderService.createOrder(1L, "itemA", 20000);
        assertThat(orderA.getDiscountPrice()).isEqualTo(1000);
    }
}
