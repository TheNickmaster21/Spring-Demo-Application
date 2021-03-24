package in.nickma.DemoRest.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "HelloHistory")
public class HelloHistory {

    protected HelloHistory(){}

    public HelloHistory(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }
}
