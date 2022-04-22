package hello.core.beanfind;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextSameBeanFIndTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameAppConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘이상이면, 중복 오류 발생")
    void findBeanByType_duplicationError() {
        assertThrows(NoUniqueBeanDefinitionException.class,
            () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘이상이면, 빈 이름을 지정하면 된다")
    void findBeanByName() {
        DiscountPolicy discountPolicy1 = ac.getBean("discountPolicy1", DiscountPolicy.class);
        DiscountPolicy discountPolicy2 = ac.getBean("discountPolicy2", DiscountPolicy.class);

        assertThat(discountPolicy1).isInstanceOf(DiscountPolicy.class);
        assertThat(discountPolicy1.getClass()).isEqualTo(RateDiscountPolicy.class);
        assertThat(discountPolicy2.getClass()).isEqualTo(FixDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key);
            System.out.println("beansOfType.get(key) = " + beansOfType.get(key));
        }
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameAppConfig {

        @Bean
        DiscountPolicy discountPolicy1() {
            return new RateDiscountPolicy();
        }

        @Bean
        DiscountPolicy discountPolicy2() {
            return new FixDiscountPolicy();
        }
    }
}
