package backend.academy.seminar2.live.service;

import backend.academy.seminar2.live.model.User;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class UserService {

    private final Map<Integer, User> users = new HashMap<>();

    public void addUser(User user) {
        if (user != null) {
            users.put(user.getId(), user);
        }
    }

    /*
    Пример использования:
    User user = findUserById(userId);
    if (user != null) {
    } else {
    }
     */
    public User findUserById(int userId) {
        return users.get(userId);
    }

    public String getUserEmail(int userId) {
        User user = users.get(userId);
        if (user != null) {
            return user.getEmail();
        }
        return null;
    }

    public String getUserName(int userId) {
        User user = users.get(userId);
        if (user != null) {
            return user.getName();
        }
        return null;
    }

    /**
     * Получение email или выброс исключения, если пользователь не найден
     */
    public String findUserEmailOrThrow(int userId) {
        User user = users.get(userId);
        if (user != null) {
            return user.getEmail();
        }
        throw new NoSuchElementException("User not found");
    }

    /**
     * Получение имени пользователя или значение по умолчанию
     */
    public String findUserNameOrDefault(int userId, String defaultName) {
        User user = users.get(userId);
        if (user != null) {
            return user.getName();
        }
        return defaultName;
    }
}
