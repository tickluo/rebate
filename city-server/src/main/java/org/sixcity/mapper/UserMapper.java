package org.sixcity.mapper;

import model.CrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sixcity.domain.User;

@Mapper
public interface UserMapper extends CrudDao<User> {

    int insert(User user);

    int updatePassword(@Param("id") Long id, @Param("password") String password);

    int updateUser(User user);

    User findByUsername(String username);

    User findById(@Param("id") Long id);
}
