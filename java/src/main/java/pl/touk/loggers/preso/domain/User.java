package pl.touk.loggers.preso.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.Set;

import static pl.touk.loggers.preso.domain.User.ENTITY_NAME;

//@lombok.Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity(name = ENTITY_NAME)
public class User {
    public static final String ENTITY_NAME = "user";

    private static final String SEQUENCE_NAME = ENTITY_NAME + "_seq";

    @Id
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    private Long id;

    @Column(nullable = false)
    private String username;

    private String email;

//    @Column(nullable = false)
//    private String data;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "userId", nullable = false)
    private Set<Data> data;

    public User() {
    }

    public User(String username, String email, Set<Data> data) {
        this.username = username;
        this.email = email;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Set<Data> getData() {
        return data;
    }


}
