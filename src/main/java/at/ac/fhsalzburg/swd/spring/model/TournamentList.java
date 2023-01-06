package at.ac.fhsalzburg.swd.spring.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TournamentList {

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date beginn;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date ende;
    @Id
    private String name;

    protected TournamentList() {}

    public TournamentList(Date beginn, Date ende, String name) {
        this.beginn = beginn;
        this.ende = ende;
        this.name = name;
    }

    public Date getBeginn(){
        return beginn;
    }
    public void setBeginn(Date beginn) {
        this.beginn = beginn;
    }

    public Date getEnde(){
        return ende;
    }
    public void setEnde(Date ende) {
        this.ende = ende;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Turnierliste{" +
            "beginn=" + beginn +
            ", ende=" + ende +
            ", name='" + name + '\'' +
            '}';
    }
}
