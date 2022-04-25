package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

// Entity annotation이 있어야 jpa가 관리할 대상이라고 인식함
@Entity
@Getter
@Setter
public class Member {

    @Id
    private Long id;
    private String name;
}
