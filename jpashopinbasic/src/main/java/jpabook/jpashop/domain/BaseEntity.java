package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
// 공통 속성 관련 어노테이션!
// Entity가 아님. 상속관계 아님.
// 부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공
// 조회, 검색 불가 .em.find(BaseEntity.class,member.getId()) 이런 게 안된다는 의미임.
// 추상 클래스로 사용하는 것을 권장

// 테이블과 관계 없고, 엔티티들이 공통으로 사용하는 정보를 모으는 역할.

// JPA 에서 상속관계 매핑인 경우에는 @Entity 클래스를.
// 그냥 공통 속성 매핑인 경우에는 @MappedSuperclass를 지원한다.
public abstract class BaseEntity {
    @Column(name="INSERT_MEMBER")
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
