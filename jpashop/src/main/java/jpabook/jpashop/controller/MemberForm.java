package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
/*
*  Entity와 Form을 나눌 것
*  Form을 사용하지 않고 Entity를 바로 받으면 Entity에 화면 종속적인 기능이 계속 생김
*  실제 Form이 요구하는 유효성과 Entity의 유효성이 다를 수 있음. (비밀번호 암호화의 경우에도)
*  JPA 사용 시 Entity를 좀 순수하게 유지하는 것이 중요함. (유지보수하기에 편해짐)
*  화면에 대응하는 객체는 Form 객체나 Dto를 사용하는 것이 바람직함.
*
* */
@Getter
@Setter
public class MemberForm {
    @NotEmpty(message="회원 이름은 필수입니다")
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
