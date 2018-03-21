package pl.zdanek.loggers.preso.repository;

import org.springframework.data.repository.Repository;
import pl.zdanek.loggers.preso.domain.User;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends Repository<User, Long> {

    Set<User> findAll();
    Optional<User> findById(Long id);

    User save(User user);

    void delete(User user);
}
