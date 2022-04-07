package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// SpringBootApplication annotation 이 붙은 class와 같은 위치 또는 하위에 위치한 모든 클래스의 
// @Service,@Component, @Repository 등 어노테이션이 붙은 클래스에 대해 자동으로 bean 생성함
@SpringBootApplication
public class JpashopApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpashopApplication.class, args);
    }

}
