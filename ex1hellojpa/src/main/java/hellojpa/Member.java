package hellojpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/* @Entity 에 관해
 - @Entity가 붙은 클래스는 JPA가 관리할 대상이라고 인식함
 - 기본 생성자 필수
 - db에 저장하고 싶은 필드에 final 사용 x
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name="member_seq_generator",sequenceName = "member_seq")
public class Member extends BaseEntity{

    /*
    기본 키 매핑 관련
        1) 직접 값 지정 :@Id 만 사용
        2) 자동 생성 : @GeneratedValue 추가 ( Oracle의 sequence, Mysql의 auto increment)
          - GenerationType.IDENTITY : DB에게 PK 생성 방법 위임하는 것 (예 - mysql의 경우 auto increment 로 들어감.)
             IDENTITY 전략을 샤용하는 경우 em.persist() 호출 시 db에 insert 쿼리가 날아감
             ( 원래는 persist()가 아닌 transaction commit()시쿼리문일 날아감)
               이유 =>JPA가 영속성 컨텍스트로서 관리를 하려면 db로부터 얻어온 PK값이 필요한데 , IDENTITY 전략의 경우 PK 생성을 DB에게 위임하고 그 전까지는
               PK가 NULL 이기 때문에 영속성 컨텍스트에 추가할 수 없음. 그래서 IDENTTITY인 경우에는 예외적으로 persist()호출 시 insert 쿼리가 동작
               하는 것임.
          - GenerationType.SEQUENCE : create sequence 문이 실행됨. sequence를 따로 명시하지 않으면 hibernate에서 제공하는 default sequnce가 생성됨.
            @SequenceGenerator 를 사용해서 테이블마다 시퀀스를 생성해서 관리할 수 있음.
            em.persist 호출 시 hibernate에서 seq객체를 db에 생성하고 그 값을 pk로서 가져와서 영속성 컨텍스트로서 관리할 수 있음.
            이 경우에는 원래대로 transaction commit 시에 insert 쿼리 날아감  (identity와 비교해서 볼 것.)
          참고 ) id의 경우 Integer 대신에 Long 사용 할 것.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "member_seq_generator") // DB에게 pk 생성 방법 위임하는 것
    private Long id;

    @Column(name="name",insertable = true , updatable = true, nullable = false)
    private String username;

//    private Integer age;

    // 이넘 타입에는 @Enumerated 사용
    // 이넘 타입은 default가 ORDINARY => ENUM의 순서 ! (0,1,2) , integer! , enum type이 추가될 경우에 위험할 수 있으므로 왠만하면 ordinary 사용 자제할 것.
    // ENUM의 값을 DB에 그대로 저장하고 싶으면 꼭 EnumType.STRING 으로 !
//   @Enumerated(EnumType.STRING)
//    private RoleType roleType;

    //날짜 타입에는 @Temporal 사용
    // 날짜만인 경우에는 TemporalType.DATE  시간인 경우에는 Time, 날짜와 시간 둘다인 경우에는 TIMESTAMP 사용! CTRL 누르고 클릭해보면 자세히 볼 수있음
    // java 8 이상인 경우에는 그냥 LocalDate 또는 LocalDateTime() 사용해도 ok
    // java 8 이전버전을 사용하는 경우 Date를 사용하고 , @Temporal에 원하는 타입을 지정해주면 된다
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;

    /*
    참고 - java 8 버전 이상인 경우에는 그냥 LocalDate or LocalDateTime 사용해도 ok
    별도의 annotation 없이도 , JPA에서 TYPE을 보고 각각 date 와 timestamp로 매칭해서 컬럼의 데이터 타입을 지정해줌!
     */
//    private LocalDate testLocalDate; // 년,월,일
//    private LocalDateTime testLocalDateTime; //년,월,일,시간까지!

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
    
    //VARCHAR를 넘어서는 큰 데이터를 넣고 싶을 때는 Lob 사용
//    @Lob
//    private String description;



    public Member(Long id, String name) {
        this.id = id;
        this.username = name;
    }
}
