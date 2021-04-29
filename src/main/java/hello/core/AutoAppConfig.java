package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(
        basePackages = "hello.core",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes=Configuration.class) //bean으로 지정하지 않을 뺄거를 지정하는것
        //Appconfig도 @configuration 붙어잇어서 컨포넌트스캔 범위에 들어가버리기때문에 configuration annotation 이 붙은 클래스 제외 (실무에선 일케 안함)
        //Configuration 어노테이션 소스 안에 @Component 가 들어있기때문에 범위에 들어가버림
)

public class AutoAppConfig {
    @Bean(name="memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
