package pl.zdanek.loggers.preso.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zdanek.loggers.preso.domain.User;
import pl.zdanek.loggers.preso.service.UserService;

import java.util.Collection;

@RestController
public class UserRest {

    private UserService userService;

    UserRest(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/all")
    public Collection<User> getAll() {
        return userService.getAll();
    }
}
