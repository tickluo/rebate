package org.sixcity.mapper;

import org.sixcity.domain.User;

public interface UserMapper {

    User insert(User user);

    User findByUsername(String username);
}
