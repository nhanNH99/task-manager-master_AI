package pl.rengreen.taskmanager.service;

import pl.rengreen.taskmanager.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User changeRoleToAdmin(User user);

    List<User> findAll();

    User getUserByEmail(String email);

    boolean isUserEmailPresent(String email);

    User getUserById(Long userId);

    void deleteUser(Long id);

    /**
     * Changes the password for the user with the given email.
     * @return error message if failed, null if successful
     */
    String changePassword(String email, String currentPassword, String newPassword, String confirmPassword);
}
