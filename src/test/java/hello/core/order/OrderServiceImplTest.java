package hello.core.order;


import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        //생성자 주입을 할경우 컴파일 단계에서 에러를 확인할 수 있다.
        //final을 사용함으로써 컴파일 단계에서 생성자에 누락이 되어있는 부분을 확인할 수 있다.
//        OrderService orderService = new OrderServiceImpl();
        OrderService orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}