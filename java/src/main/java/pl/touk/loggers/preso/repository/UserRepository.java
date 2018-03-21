package pl.touk.loggers.preso.repository;

import org.springframework.data.repository.Repository;
import pl.touk.loggers.preso.domain.User;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends Repository<User, Long> {

    Set<User> findAll();
    Optional<User> findById(Long id);

    User save(User user);

    void delete(User user);

    Optional<User> findByPhoneNo(String phoneNo);

    Optional<User> findByName(String name);
}
