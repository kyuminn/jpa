package jpabook.jpashop.dto;

import jpabook.jpashop.domain.Address;
import lombok.Getter;
import lombok.Setter;
// ModelMapper는 기본 생성자와 setter 함수를 통해 매핑하므로, 앞서 말한 두 가지가 반드시 있어야 함!
@Getter
@Setter
public class MemberDto {
    private Long id;
    private String name;
    private Address address;
}
