package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(protoConfig.class);

        protoConfig protoConfig1 = ac.getBean(protoConfig.class);
        protoConfig protoConfig2 = ac.getBean(protoConfig.class);
        System.out.println("protoConfig1 = "+protoConfig1);
        System.out.println("protoConfig2 = "+protoConfig2);
        Assertions.assertThat(protoConfig1).isSameAs(protoConfig2);
//        ac.close();
        protoConfig1.destroy();
        protoConfig2.destroy();
    }

    @Scope("prototype")
    static class protoConfig{
        @PostConstruct
        public void init(){
            System.out.println("protoConfig.init");
        }
        @PreDestroy
        public void destroy(){
            System.out.println("protoConfig.destroy");
        }
    }
}
