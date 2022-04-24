package hello.core.scan.filter;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(
            ComponentFilterAppConfig.class);

        MyIncludeBean myIncludeBean = ac.getBean("myIncludeBean", MyIncludeBean.class);
        Assertions.assertThat(myIncludeBean).isNotNull();

//        MyExcludeBean myExcludeBean = ac.getBean("myExcludeBean", MyExcludeBean.class);
//        Assertions.assertThat(myExcludeBean).isNotNull();

        assertThrows(NoSuchBeanDefinitionException.class,
            () -> ac.getBean("myExcludeBean", MyExcludeBean.class));
    }

    @Configuration
    @ComponentScan(
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }

}
