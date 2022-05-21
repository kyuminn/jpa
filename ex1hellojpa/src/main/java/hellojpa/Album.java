package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@DiscriminatorValue("A")
public class Album extends Item {

    private String artist;
}
