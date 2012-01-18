package net.therap.lab.onetoone.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author jahangir
 * @since 1/13/12 10:27 AM
 */
@Entity
@Table(name = "APP_USER")
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", length = 60)
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
