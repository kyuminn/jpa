package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

//
@Embeddable
@Getter
@Setter
public class Address {
    @Column(length = 10)
    private String city;
    @Column(length = 20)
    private String street;
    @Column(length = 5)
    private String zipcode;

    public String fullAddress(){
        return getCity()+" "+getStreet()+" "+getZipcode();
    }

    // 값 타입 validation
    public boolean isValid(){
        return true;
    }

    // use getters during code Generation option 선택하는 것이 좋음 (이걸 선택 안하면 객체에 직접 접근하는데 proxy 인 경우에는 문제가 생길수 있음)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getCity(), address.getCity()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getZipcode(), address.getZipcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getZipcode());
    }
    // generate 단축키 : alt + insert
}
