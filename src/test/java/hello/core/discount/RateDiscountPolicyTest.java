package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP TEST")
    void vip() {
        Member member = new Member(1L, "VIP01", Grade.VIP);
        int discount = discountPolicy.discount(member, 10000);

        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("BASIC TEST")
    void basic() {
        Member member = new Member(2L, "BASIC01", Grade.BASIC);
        int discount = discountPolicy.discount(member, 10000);

        assertThat(discount).isEqualTo(0);
    }
}
