package hellojpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
/* @Entity 에 관해
 - @Entity가 붙은 클래스는 JPA가 관리할 대상이라고 인식함
 - 기본 생성자 필수
 - db에 저장하고 싶은 필드에 final 사용 x
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    private Long id;
    private String name;

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
