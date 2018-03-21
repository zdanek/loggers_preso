package pl.zdanek.loggers.preso.service;

import org.springframework.stereotype.Service;
import pl.zdanek.loggers.preso.domain.Data;
import pl.zdanek.loggers.preso.domain.User;
import pl.zdanek.loggers.preso.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

//@AllArgsConstructor
@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void updateUserData(long userId, String data) {

        Optional<User> optUser = userRepository.findById(userId);

        if (optUser.isPresent()) {
            User user = optUser.get();

            Data dataObj = new Data();
            dataObj.setData(data);
            user.getData().add(dataObj);
        }
    }

    public Collection<User> getAll() {
        return userRepository.findAll();
    }
}
