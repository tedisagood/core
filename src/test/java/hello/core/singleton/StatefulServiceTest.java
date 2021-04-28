package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자가 10000원 주문
        int userPrice1 = statefulService1.order("userA",10000);
        //ThreadB : B사용자가 20000원 주문
        int userPrice2 =statefulService2.order("userB",20000);

        //ThreadA : 사용자 A가 주문금액을 조회 --> 그럼 마지막에 한 B의 주문가격이 나올거임 ㅋㅋㅋ
        //서비스가 1이든 2든 결국 객체는 1개만 생성되어있기때문에 공유값을 바꿔버리면 다 적용되어버림
//        int price = statefulService1.getPrice();
        System.out.println("UserA = "+userPrice1);
        System.out.println("UserB = "+userPrice2);
        //ThreadB : B사용자가 주문금액 조회

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService (){
            return new StatefulService();
        }
    }


}