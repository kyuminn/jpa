package jpql;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Team {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    //@BatchSize(size = 100) // persistence.xml에서 global하게 설정할 수도 있음. <property name="hibernate.default_batch_fetch_size" value="100"/>
    private List<Member> members = new ArrayList<>();

}
