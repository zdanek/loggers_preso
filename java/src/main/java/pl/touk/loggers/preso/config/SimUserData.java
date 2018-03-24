package pl.touk.loggers.preso.config;

import java.util.ArrayList;
import java.util.List;

public class SimUserData {
    public static int numOfUsers;
    public static final String BAD_USER = "Bartek";

    private static List<String> users = new ArrayList<>();

    public static List<String> getUsers() {
        if (users.size() == 0){
            users.add("Kasia");
            users.add("Ania");
            users.add("Zosia");
            users.add("Jan");
            users.add("Piotr");
            users.add("Tomek");

            users.add(BAD_USER);
            numOfUsers = users.size() - 1;

        }
        return users;
    }
}
