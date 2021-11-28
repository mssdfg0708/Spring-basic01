package hello.core;

import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "member AA", Grade.VIP);
        memberService.signUp(member);

        Order order = orderService.createOrder(memberId, "item AA", 20000);
        System.out.println(order);
        System.out.println("calculate Price : " + order.calculatePrice());
    }
}
