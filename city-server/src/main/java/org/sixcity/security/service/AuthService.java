package org.sixcity.security.service;

import org.sixcity.domain.User;

//TODO: rebuild this service only for token
public interface AuthService {
    /*int register(User userToAdd);*/
    String login(String username, String password);
    void logout();
    String refresh(String oldToken);
}
