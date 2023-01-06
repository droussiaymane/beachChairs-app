package at.ac.fhsalzburg.swd.spring.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Beach {



    @Id
    private String strandName;


    @OneToMany(mappedBy= "beach")
    @NotFound(action = NotFoundAction.IGNORE)
    private Set<BeachChair> beachChair;

    protected Beach() {}

    public Beach(String strandName) {
        this.strandName = strandName;
    }

    public String getStrandName(){
        return strandName;
    }

    public void setStrandName(String strandName) {
        this.strandName = strandName;
    }
}
