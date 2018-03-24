package pl.touk.loggers.preso.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.touk.loggers.preso.config.SimUserData;
import pl.touk.loggers.preso.domain.User;
import pl.touk.loggers.preso.repository.UserRepository;

import java.util.List;

import static pl.touk.loggers.preso.config.SimUserData.BAD_USER;

@Component
public class DataLoader implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    private UserRepository userRepository;

    DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        logger.info("Initializing DB");

        List<String> users = SimUserData.getUsers();

        logger.debug("Got [{}] users", users.size());
        for (int i = 0; i < users.size(); i++) {
            String name = users.get(i);
            String number = genNum(i);

            if (users.equals(BAD_USER)) {
                continue;
            }


            User user = userRepository.findByName(name).orElseGet(() -> {

                User newUser = new User(name, number, name + "@touk.pl");
                logger.debug("Inserting a new User with name [{}]", name);

                userRepository.save(newUser);

                logger.debug("Generated ID [{}]", newUser.getId());

                return newUser;
            });

            logger.trace("You can find saved user [{}] with phoneNo", user, user.getPhoneNo());
        }


    }

    private String genNum(Integer idx) {
        if (idx > 9) {
            throw new RuntimeException(String.format("Index exceeds 9: [%s]", idx));
        }
        return "60000000" + idx.toString();
    }
}
