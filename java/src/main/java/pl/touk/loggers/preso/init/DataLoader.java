package pl.touk.loggers.preso.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.touk.loggers.preso.domain.User;
import pl.touk.loggers.preso.repository.UserRepository;

import java.util.Collections;

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

        final String name = "zdanek";
        User user = userRepository.findByName(name).orElseGet(() -> {

            User newUser = new User(name, "600123456", name + "@touk.pl", Collections.emptySet());
            logger.debug("Inserting a new User with name [{}]", name);

            userRepository.save(newUser);

            logger.debug("Generated ID [{}]", newUser.getId());

            return newUser;
        });

        logger.debug("You can find saved user [{}]");
    }
}
