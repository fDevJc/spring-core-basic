package hello.core.scope;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
            PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.add();
        System.out.println(prototypeBean1.getCount());

        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.add();
        System.out.println(prototypeBean2.getCount());

        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        ac.close();
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
            SingletonBean.class, PrototypeBean.class);

        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        int singletonBean1Count = singletonBean1.logic();

        assertThat(singletonBean1Count).isEqualTo(1);

        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        int singletonBean2Count = singletonBean2.logic();

        assertThat(singletonBean1Count).isEqualTo(1);
        assertThat(singletonBean2Count).isEqualTo(1);

    }

    @Scope("singleton")
    static class SingletonBean {
        private final Provider<PrototypeBean> prototypeBeanObjectProvider;

        public SingletonBean(Provider<PrototypeBean> prototypeBeanObjectProvider) {
            this.prototypeBeanObjectProvider = prototypeBeanObjectProvider;
        }
        
        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanObjectProvider.get();
            prototypeBean.add();
            return prototypeBean.getCount();

        }
    }

    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void add() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void close() {
            System.out.println("PrototypeBean.close");
        }
    }
}
