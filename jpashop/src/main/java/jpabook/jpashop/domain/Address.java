package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address(){
    }


    //생성할 때만 값을 세팅하고 , 평소에는 setter를 잘열어두지 않는 것이 좋은 방향임.
    public Address(String city,String street, String zipcode){
        this.city=city;
        this.street=street;
        this.zipcode = zipcode;
    }
}
