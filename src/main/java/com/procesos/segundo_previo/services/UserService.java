package com.procesos.segundo_previo.services;

import com.procesos.segundo_previo.models.User;

import java.util.List;

public interface UserService {
    User getUser(Long id);
    Boolean createUser(User user);
    List<User> allUsers();
    Boolean updateUser(Long id, User user);
    String login(User user);
    Boolean Auth (String token);
}
