package pl.touk.loggers.preso.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.touk.loggers.preso.domain.User;
import pl.touk.loggers.preso.service.UserService;

import java.util.Collection;

@RestController
public class UserRest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private UserService userService;

    UserRest(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public Collection<User> getAll() {

        logger.debug("Returning all users");

        Collection<User> allUsers = userService.getAll();

        logger.trace("Got [{}] users", allUsers.size());
        return allUsers;
    }

    @GetMapping(value = "/users/{phoneNo}")
    public User getUserByPhoneNo(@PathVariable String phoneNo) {

        logger.debug("Returning user for phoneNo [{}]", phoneNo);

        User user = userService.getUserByPhoneNo(phoneNo);

        logger.trace("Got user[{}]", user);
        return user;
    }

    @GetMapping(value = "/users/{phoneNo}/billing")
    public UserWithBilling getUserByPhoneNoWithBilling(@PathVariable String phoneNo) {
        return userService.getUserWithBilling(phoneNo);
    }


}
