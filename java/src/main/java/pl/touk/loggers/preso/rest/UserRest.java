package pl.touk.loggers.preso.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.touk.loggers.preso.domain.User;
import pl.touk.loggers.preso.service.UserService;

import java.util.Collection;

@RestController
public class UserRest {

    private UserService userService;

    UserRest(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public Collection<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(value = "/users/{phoneNo}")
    public User getUserByPhoneNo(@PathVariable String phoneNo) {
        return userService.getUserByPhoneNo(phoneNo);
    }

    @GetMapping(value = "/users/{phoneNo}/billing")
    public UserWithBilling getUserByPhoneNoWithBilling(@PathVariable String phoneNo) {
        return userService.getUserWithBilling(phoneNo);
    }


}
