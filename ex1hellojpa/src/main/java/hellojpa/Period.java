package hellojpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class Period {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

//    public boolean isWork(LocalDateTime localDateTime){
//
//    }
}
