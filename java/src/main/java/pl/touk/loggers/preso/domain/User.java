package pl.touk.loggers.preso.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import static pl.touk.loggers.preso.domain.User.ENTITY_NAME;

@NoArgsConstructor
@Entity(name = ENTITY_NAME)
public class User {
    public static final String ENTITY_NAME = "user";

    private static final String SEQUENCE_NAME = ENTITY_NAME + "_seq";

    @Id
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNo;

    private String email;

    public User(String name, String phoneNo, String email) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
