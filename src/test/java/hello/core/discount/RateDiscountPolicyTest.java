package hello.core.discount;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 되야한다")
    void discount_vip() {
        //given
        Member member = new Member(1L, "name", Grade.VIP);

        //when
        int discount = rateDiscountPolicy.discount(member, 1000);

        //then
        assertThat(discount).isEqualTo(100);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 되지않아야한다")
    void discount_basic() {
        //given
        Member member = new Member(1L, "name", Grade.BASIC);

        //when
        int discount = rateDiscountPolicy.discount(member, 1000);

        //then
        assertThat(discount).isEqualTo(0);
        assertThat(discount).isNotEqualTo(100);
    }
}